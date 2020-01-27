package com.lana.logon;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.lana.logon.models.User;
import com.lana.logon.models.product.Category;
import com.lana.logon.models.product.Product;
import com.lana.logon.repositories.UserRepo;
import com.lana.logon.repositories.product.CategoryRepo;
import com.lana.logon.repositories.product.ProductRepo;
import com.lana.logon.util.GraphQLPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {
    private UserRepo userRepo;
    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    @Autowired
    public Query(UserRepo userRepo, ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    public Page<User> users(GraphQLPageRequest pageRequest) {
        Optional<Pageable> pageable = Optional.ofNullable(pageRequest);
        return userRepo.findAll(pageable.orElse(Pageable.unpaged()));
    }

    public Page<Product> products(GraphQLPageRequest pageRequest) {
        Optional<Pageable> pageable = Optional.ofNullable(pageRequest);
        return productRepo.findAll(pageable.orElse(Pageable.unpaged()));
    }

    public Page<Category> categories(GraphQLPageRequest pageRequest) {
        Optional<Pageable> pageable = Optional.ofNullable(pageRequest);
        return categoryRepo.findAll(pageable.orElse(Pageable.unpaged()));
    }
}
