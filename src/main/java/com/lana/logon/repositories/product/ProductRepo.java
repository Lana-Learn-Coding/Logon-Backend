package com.lana.logon.repositories.product;

import com.lana.logon.models.product.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepo extends PagingAndSortingRepository<Product, String> {
}
