package com.lana.logon.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProductDetailDto {
    private Integer id;
    private String name;
    private Double price;
    private Float rate;

    private Set<String> images;
    private String category;
    private String description;
}
