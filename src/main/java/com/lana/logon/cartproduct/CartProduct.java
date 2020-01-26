package com.lana.logon.cartproduct;

import com.lana.logon.Auditable;
import com.lana.logon.product.Product;
import com.lana.logon.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CartProduct extends Auditable {
    @EmbeddedId
    private CartProductKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;
}