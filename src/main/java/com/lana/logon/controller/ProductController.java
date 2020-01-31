package com.lana.logon.controller;

import com.lana.logon.dto.ProductDetailDto;
import com.lana.logon.dto.mapper.ProductMapper;
import com.lana.logon.repository.product.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/products")
public class ProductController {
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(ProductRepo productRepo, ProductMapper productMapper) {
        this.productMapper = productMapper;
        this.productRepo = productRepo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailDto> getProduct(@PathVariable Integer id) {
        return productRepo.findById(id)
                .map(product -> ResponseEntity.ok(productMapper.mapToProductDetailDto(product)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
