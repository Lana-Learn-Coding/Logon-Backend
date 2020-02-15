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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    private String title;

    @Column(length = 512)
    private String comment;

    @Column(nullable = false)
    private Integer rate;
}
