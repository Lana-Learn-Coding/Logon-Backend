package com.lana.logon.dto.mapper;

import com.lana.logon.dto.CategoryDto;
import com.lana.logon.model.Category;
import com.lana.logon.repository.CategoryRepo;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

    private CategoryRepo categoryRepo;

    @Autowired
    public void initialize(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public abstract CategoryDto categoryToCategoryDto(Category category);

    public String categoryToString(Category category) {
        return category.getName();
    }

    public Category stringToCategory(String string) {
        return categoryRepo
                .findByName(string)
                .orElse(null);
    }
}
