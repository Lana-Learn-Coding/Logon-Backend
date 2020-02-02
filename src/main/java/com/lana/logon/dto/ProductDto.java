package com.lana.logon.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Integer id;
    private String name;
    private Double price;
    private String image;
    private String category;

    private Float rate;
}
