package com.lana.logon.model.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}