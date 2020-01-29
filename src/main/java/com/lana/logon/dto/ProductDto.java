package com.lana.logon.dto;

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
    private Set<String> categories;
    private String image;

    private Float rate;
//    private Set<ProductSpecification> specifications;
}
