package com.lana.logon.model.product;

import com.lana.logon.model.Auditable;
import com.lana.logon.model.Category;
import com.lana.logon.model.cart.CartProduct;
import com.lana.logon.model.product.rate.ProductRate;
import com.lana.logon.model.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Product extends Auditable {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Double price;

    @OneToMany(mappedBy = "product")
    private Set<ProductRate> rates;

    @OneToMany
    private Set<ProductImage> productImages;

    @ManyToMany
    private Set<Category> categories;

    @OneToMany
    private Set<ProductSpecification> specifications;

    @ManyToMany(mappedBy = "favorites")
    private Set<User> favoriteOfs;

    @OneToMany(mappedBy = "product")
    private Set<CartProduct> carts;
}
