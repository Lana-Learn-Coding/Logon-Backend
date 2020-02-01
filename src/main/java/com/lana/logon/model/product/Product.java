package com.lana.logon.model.product;

import com.lana.logon.model.Auditable;
import com.lana.logon.model.Category;
import com.lana.logon.model.cart.CartProduct;
import com.lana.logon.model.product.rate.ProductRate;
import com.lana.logon.model.user.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

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

    @OneToMany(mappedBy = "product")
    private Set<ProductImage> images;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    @OneToOne
    private ProductSpecification specification;

    @ManyToMany(mappedBy = "favorites")
    private Set<User> favoriteOfs;

    @OneToMany(mappedBy = "product")
    private Set<CartProduct> carts;

    @Formula("SELECT CAST(SUM(r.rate) AS FLOAT) / COUNT(*) FROM product_rate r WHERE r.product_id = id")
    private Float rate;
}
