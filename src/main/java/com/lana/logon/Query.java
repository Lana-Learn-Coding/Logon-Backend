package com.lana.logon;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.lana.logon.user.User;
import com.lana.logon.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {
    @Autowired
    private UserRepo userRepo;

    public Iterable<User> users() {
        return userRepo.findAll();
    }
}
