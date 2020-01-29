package com.lana.logon.dto;

import com.lana.logon.model.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Set<Category> categories;

//    private Set<ProductRate> ratings;
//    private Set<ProductImage> productImages;
//    private Set<ProductSpecification> specifications;
}
