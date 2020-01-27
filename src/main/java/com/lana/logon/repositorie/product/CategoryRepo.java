package com.lana.logon.repositorie.product;


import com.lana.logon.model.product.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepo extends PagingAndSortingRepository<Category, String> {
}
