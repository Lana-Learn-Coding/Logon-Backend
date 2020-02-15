package com.lana.logon.dto.product;

import com.lana.logon.model.cart.CartProductKey;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartProductDto {
    private CartProductKey id;

    private ProductDto product;

    private Integer quantity = 1;
}
