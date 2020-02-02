package com.lana.logon.model.product;

import com.lana.logon.model.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ProductSpecification extends Auditable {
    @Id
    private Integer productId;

    private String ram;
    private String cpu;
    private String gpu;
    private String screen;

    @OneToOne(mappedBy = "specification")
    @JoinColumn(name = "product_id")
    @MapsId
    private Product product;
}
