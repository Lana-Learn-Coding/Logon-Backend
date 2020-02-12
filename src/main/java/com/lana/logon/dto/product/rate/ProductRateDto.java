package com.lana.logon.dto.product.rate;

import com.lana.logon.dto.product.ProductDto;
import com.lana.logon.dto.user.UserDto;
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
