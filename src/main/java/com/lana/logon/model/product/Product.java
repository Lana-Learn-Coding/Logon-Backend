package com.lana.logon.model.product;

import com.lana.logon.model.Auditable;
import com.lana.logon.model.Category;
import com.lana.logon.model.User;
import com.lana.logon.model.cart.CartProduct;
import com.lana.logon.model.product.rate.ProductRate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
    private Set<ProductRate> ratings;

    @OneToMany
    @JoinColumn
    private Set<ProductImage> productImages;

    @ManyToMany
    @JoinTable
    private Set<Category> categories;

    @OneToMany
    @JoinColumn
    private Set<ProductSpecification> specifications;

    @ManyToMany(mappedBy = "favorites")
    private Set<User> favoriteOfs;

    @OneToMany(mappedBy = "product")
    private Set<CartProduct> carts;
}
