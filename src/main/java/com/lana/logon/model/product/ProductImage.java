package com.lana.logon.model.product;

import com.lana.logon.model.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductImage extends Auditable {
    @Id
    @GeneratedValue
    private Integer id;

    private String image;

    @ManyToOne
    private Product product;

    public ProductImage(String image, Product product) {
        this.product = product;
        this.image = image;
    }
}
