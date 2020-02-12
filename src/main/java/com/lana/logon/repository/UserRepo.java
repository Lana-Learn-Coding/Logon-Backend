package com.lana.logon.repository;

import com.lana.logon.model.user.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepo extends PagingAndSortingRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
