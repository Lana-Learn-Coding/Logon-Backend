package com.lana.logon.models;

import com.lana.logon.models.cart.CartProduct;
import com.lana.logon.models.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends Auditable {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String email;
    private String password;

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
}
