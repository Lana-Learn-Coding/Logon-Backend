package com.lana.logon.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRateDto {
    private UserDto user;
    private ProductDto product;

    private String title;
    private String comment;
    private Integer rate;
}
