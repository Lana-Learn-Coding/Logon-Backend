package com.lana.logon.repository;

import com.lana.logon.model.user.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepo extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
