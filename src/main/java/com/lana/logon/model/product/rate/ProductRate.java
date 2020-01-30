package com.lana.logon.model.product.rate;


import com.lana.logon.model.Auditable;
import com.lana.logon.model.product.Product;
import com.lana.logon.model.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ProductRate extends Auditable {
    @EmbeddedId
    private ProductRateKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(length = 512)
    private String comment;

    private Integer rate;
}
