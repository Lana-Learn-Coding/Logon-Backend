package com.lana.logon.repository;


import com.lana.logon.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CategoryRepo extends PagingAndSortingRepository<Category, Integer> {
    Optional<Category> findByName(String name);
}
