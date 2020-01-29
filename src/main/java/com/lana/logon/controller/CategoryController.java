package com.lana.logon.controller;

import com.lana.logon.dto.CategoryDto;
import com.lana.logon.dto.ProductDto;
import com.lana.logon.dto.mapper.CategoryMapper;
import com.lana.logon.dto.mapper.ProductMapper;
import com.lana.logon.repository.product.CategoryRepo;
import com.lana.logon.repository.product.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/categories")
public class CategoryController {
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryRepo categoryRepo,
                              ProductRepo productRepo,
                              ProductMapper productMapper,
                              CategoryMapper categoryMapper) {
        this.productMapper = productMapper;
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDto>> getCategories(Pageable pageable) {
        return ResponseEntity.ok(
                categoryRepo.findAll(pageable).map(categoryMapper::categoryToCategoryDto)
        );
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable String name) {
        return categoryRepo.findByName(name)
                .map(category -> ResponseEntity.ok(categoryMapper.categoryToCategoryDto(category)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{name}/products")
    public ResponseEntity<Page<ProductDto>> getCategoryProduct(@PathVariable String name,
                                                               @PageableDefault Pageable pageable) {
        return categoryRepo.findByName(name)
                .map(value -> ResponseEntity.ok(
                        productRepo
                                .findAllByCategoriesContains(value, pageable)
                                .map(product -> productMapper.productToProductDTO(product))
                ))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
