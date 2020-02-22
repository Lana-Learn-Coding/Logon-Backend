package com.lana.logon.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(RoleName roleName) {
        id = resolveRole(roleName);
    }

    private Integer resolveRole(RoleName roleName) {
        switch (roleName) {
            case USER:
                return 2;
            case ADMIN:
                return 3;
            default:
                return 1;
        }
    }
}