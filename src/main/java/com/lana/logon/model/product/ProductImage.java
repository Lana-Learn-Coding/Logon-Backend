package com.lana.logon.model.product;

import com.lana.logon.model.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductImage extends Auditable {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne
    private Product product;

    public ProductImage(String imageUrl, Product product) {
        this.product = product;
        this.imageUrl = imageUrl;
    }
}
