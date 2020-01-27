package com.lana.logon.repositories.product;


import com.lana.logon.models.product.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepo extends PagingAndSortingRepository<Category, String> {
}
