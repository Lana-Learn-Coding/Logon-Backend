package com.lana.logon.repositorie.product;

import com.lana.logon.model.product.Category;
import com.lana.logon.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ProductRepo extends PagingAndSortingRepository<Product, String> {
    Page<Product> findAllByCategoriesContains(Category category, Pageable pageable);
}
