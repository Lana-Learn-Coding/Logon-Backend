package com.lana.logon.repositories;

import com.lana.logon.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepo extends PagingAndSortingRepository<User, String> {
}
