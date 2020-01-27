package com.lana.logon.models.product;

import com.lana.logon.models.Auditable;
import com.lana.logon.models.User;
import com.lana.logon.models.cart.CartProduct;
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

    private String name;
    private String description;
    private Double price;
    private Float rate;

    @ManyToMany
    @JoinTable
    private Set<Category> categories;

    @ManyToMany(mappedBy = "favorites")
    private Set<User> favoriteOfs;

    @OneToMany
    @JoinColumn
    private Set<ProductSpecification> specifications;

    @OneToMany(mappedBy = "product")
    private Set<CartProduct> carts;
}
