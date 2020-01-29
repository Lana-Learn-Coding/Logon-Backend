package com.lana.logon.controller;

import com.lana.logon.model.product.Category;
import com.lana.logon.model.product.Product;
import com.lana.logon.repositories.product.CategoryRepo;
import com.lana.logon.repositories.product.ProductRepo;
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

    public CategoryController(CategoryRepo categoryRepo,
                              ProductRepo productRepo) {
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }

    @GetMapping
    public ResponseEntity<Page<Category>> getCategories(Pageable pageable) {
        return ResponseEntity.ok(categoryRepo.findAll(pageable));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Category> getCategory(@PathVariable String name) {
        return categoryRepo.findByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{name}/products")
    public ResponseEntity<Page<Product>> getCategoryProduct(@PathVariable String name,
                                                            @PageableDefault Pageable pageable) {
        return categoryRepo.findByName(name)
                .map(value -> ResponseEntity.ok(productRepo.findAllByCategoriesContains(value, pageable)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
