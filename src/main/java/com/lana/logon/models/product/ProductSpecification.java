package com.lana.logon.models.product;

import com.lana.logon.models.Auditable;
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
public class ProductSpecification extends Auditable {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String info;

    @ManyToOne
    private Product product;
}
