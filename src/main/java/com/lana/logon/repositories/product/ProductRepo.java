package com.lana.logon.repositories.product;

import com.lana.logon.model.product.Category;
import com.lana.logon.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface ProductRepo extends PagingAndSortingRepository<Product, Integer> {
    Page<Product> findAllByCategoriesContains(Category category, Pageable pageable);

    Page<Product> findAllByCategoriesIn(List<Category> category, Pageable pageable);
}
