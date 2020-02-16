package com.lana.logon.repository.product;

import com.lana.logon.dto.product.rate.ProductRateCount;
import com.lana.logon.model.product.ProductRate;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface ProductRateRepo extends PagingAndSortingRepository<ProductRate, Integer>, JpaSpecificationExecutor<ProductRate> {

    @Query("SELECT new com.lana.logon.dto.product.rate.ProductRateCount(COUNT(r.rate)," +
            "COALESCE(SUM(case when r.rate = 1 then 1 else 0 end),0)," +
            "COALESCE(SUM(case when r.rate = 2 then 1 else 0 end),0)," +
            "COALESCE(SUM(case when r.rate = 3 then 1 else 0 end),0)," +
            "COALESCE(SUM(case when r.rate = 4 then 1 else 0 end),0)," +
            "COALESCE(SUM(case when r.rate = 5 then 1 else 0 end),0))" +
            "FROM ProductRate r WHERE r.product.id = :id")
    ProductRateCount getRateCountsByProductId(@Param("id") Integer id);
}
