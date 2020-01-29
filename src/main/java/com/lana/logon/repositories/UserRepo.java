package com.lana.logon.repositories;

import com.lana.logon.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepo extends PagingAndSortingRepository<User, Integer> {
}
