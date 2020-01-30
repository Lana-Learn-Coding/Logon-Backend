package com.lana.logon.model.cart;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class CartProductKey implements Serializable {
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "product_id")
    private Integer productId;
}
