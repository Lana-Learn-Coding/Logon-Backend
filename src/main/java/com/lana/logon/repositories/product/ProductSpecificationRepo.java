package com.lana.logon.repositories.product;

import com.lana.logon.models.product.ProductSpecification;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductSpecificationRepo extends PagingAndSortingRepository<ProductSpecification, String> {
}
