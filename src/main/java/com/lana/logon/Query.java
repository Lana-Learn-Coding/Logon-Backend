package com.lana.logon;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.lana.logon.category.Category;
import com.lana.logon.category.CategoryRepo;
import com.lana.logon.product.Product;
import com.lana.logon.product.ProductRepo;
import com.lana.logon.user.User;
import com.lana.logon.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public Iterable<User> users() {

        return userRepo.findAll();
    }

    public Iterable<Product> products() {
        return productRepo.findAll();
    }

    public Iterable<Category> categories() {
        return categoryRepo.findAll();
    }
}
