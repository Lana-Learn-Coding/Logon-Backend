package com.lana.logon.repositories.product;


import com.lana.logon.model.product.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CategoryRepo extends PagingAndSortingRepository<Category, Integer> {
    public Optional<Category> findByName(String name);
}
