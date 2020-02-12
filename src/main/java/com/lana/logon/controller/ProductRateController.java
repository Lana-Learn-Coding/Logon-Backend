package com.lana.logon.controller;

import com.lana.logon.dto.product.rate.ProductRateCount;
import com.lana.logon.dto.product.rate.ProductRateDto;
import com.lana.logon.repository.product.ProductRateRepo;
import com.lana.logon.util.mapper.ProductRateMapper;
import io.github.perplexhub.rsql.RSQLJPASupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rates")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductRateController {

    private final ProductRateRepo productRateRepo;
    private final ProductRateMapper productRateMapper;

    @Autowired
    public ProductRateController(ProductRateRepo productRateRepo,
                                 ProductRateMapper productRateMapper) {
        this.productRateRepo = productRateRepo;
        this.productRateMapper = productRateMapper;
    }

    @GetMapping
    public ResponseEntity<Page<ProductRateDto>> getAllRates(@RequestParam(defaultValue = "") String query,
                                                            Pageable pageable) {
        try {
            return ResponseEntity.ok(
                    productRateRepo
                            .findAll(RSQLJPASupport.toSpecification(query), pageable)
                            .map(productRateMapper::productRateToProductRateDto)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/count")
    public ResponseEntity<ProductRateCount> getAvgRate(@RequestParam("productid") Integer id) {
        return ResponseEntity.ok(productRateRepo.getRateCountsByProductId(id));
    }
}
