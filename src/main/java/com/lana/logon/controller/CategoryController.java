package com.lana.logon.controller;

import com.lana.logon.dto.CategoryDto;
import com.lana.logon.repository.CategoryRepo;
import com.lana.logon.util.mapper.CategoryMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/categories")
public class CategoryController {
    private final CategoryRepo categoryRepo;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryRepo categoryRepo,
                              CategoryMapper categoryMapper) {
        this.categoryRepo = categoryRepo;
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
}
