package com.lana.logon.repositorie.product;

import com.lana.logon.model.product.ProductSpecification;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductSpecificationRepo extends PagingAndSortingRepository<ProductSpecification, Integer> {
}
