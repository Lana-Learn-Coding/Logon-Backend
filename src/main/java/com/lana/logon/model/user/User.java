package com.lana.logon.model.user;

import com.lana.logon.model.Auditable;
import com.lana.logon.model.cart.CartProduct;
import com.lana.logon.model.product.Product;
import com.lana.logon.model.product.rate.ProductRate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class User extends Auditable {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String email;
    private String password;

    private String avatar;

    private String firstName;
    private String lastName;
    private Character gender;

    @Column(unique = true)
    private String phone;

    @ManyToMany
    @JoinTable(
            name = "favorites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> favorites;

    @OneToMany(mappedBy = "user")
    private Set<CartProduct> cartProducts;

    @OneToMany(mappedBy = "user")
    private Set<ProductRate> ratings;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}
