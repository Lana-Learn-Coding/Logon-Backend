package com.lana.logon.controller.user;


import com.lana.logon.dto.user.UserDto;
import com.lana.logon.model.user.User;
import com.lana.logon.repository.user.UserRepo;
import com.lana.logon.util.mapper.UserMapper;
import io.github.perplexhub.rsql.RSQLJPASupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/users")
public class UserController {
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserRepo userRepo, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userRepo = userRepo;
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllUser(Pageable pageable, @RequestParam String query) {
        try {
            return ResponseEntity.ok(
                    userRepo
                            .findAll(RSQLJPASupport.toSpecification(query), pageable)
                            .map(userMapper::userToUserDto)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer id,
                                           @AuthenticationPrincipal UserDetails authenticatedUser) {
        Optional<User> user = userRepo.findById(id);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (!user.get().getUsername().equals(authenticatedUser.getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(userMapper.userToUserDto(user.get()));
    }
}
