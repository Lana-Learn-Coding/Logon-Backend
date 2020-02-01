package com.lana.logon.model.product;

import com.lana.logon.model.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class ProductSpecification extends Auditable {
    @Id
    @GeneratedValue
    private Integer id;

    private String ram;
    private String cpu;
    private String gpu;
    private String screen;

    @OneToOne(mappedBy = "specification")
    private Product product;
}
