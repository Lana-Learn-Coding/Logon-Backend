package com.lana.logon.repository.product;

import com.lana.logon.model.product.rate.ProductRate;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRateRepo extends PagingAndSortingRepository<ProductRate, Integer>, JpaSpecificationExecutor<ProductRate> {
}
