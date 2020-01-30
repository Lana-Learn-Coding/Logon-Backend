package com.lana.logon.repository;

import com.lana.logon.model.user.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepo extends PagingAndSortingRepository<User, Integer> {
}
