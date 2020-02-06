package com.lana.logon.controller;

import com.lana.logon.dto.ProductDetailDto;
import com.lana.logon.dto.ProductDto;
import com.lana.logon.dto.mapper.ProductMapper;
import com.lana.logon.repository.product.ProductRepo;
import io.github.perplexhub.rsql.RSQLJPASupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping
    public ResponseEntity<Page<ProductDto>> getAllProduct(@RequestParam(required = false) String query,
                                                          Pageable pageable) {
        try {
            return ResponseEntity.ok(
                    productRepo
                            .findAll(RSQLJPASupport.toSpecification(query), pageable)
                            .map(productMapper::productToProductDto)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailDto> getProduct(@PathVariable Integer id) {
        return productRepo.findById(id)
                .map(product -> ResponseEntity.ok(productMapper.productToProductDetailDto(product)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
