package com.lana.logon.repository.product;

import com.lana.logon.model.product.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ProductRepo extends PagingAndSortingRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
}
