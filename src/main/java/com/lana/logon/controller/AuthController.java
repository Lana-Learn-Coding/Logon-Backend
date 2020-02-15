package com.lana.logon.controller;

import com.lana.logon.dto.user.LoginPayload;
import com.lana.logon.dto.user.UserAndToken;
import com.lana.logon.repository.user.UserRepo;
import com.lana.logon.security.jwt.JwtProvider;
import com.lana.logon.util.mapper.UserMapper;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/auth")
public class AuthController {

    public final UserMapper userMapper;
    public final JwtProvider jwtProvider;
    public final AuthenticationManager authManager;
    public final UserRepo userRepo;

    @Autowired
    public AuthController(UserMapper userMapper,
                          JwtProvider jwtProvider,
                          AuthenticationManager authenticationManager,
                          UserRepo userRepo) {
        this.userMapper = userMapper;
        this.jwtProvider = jwtProvider;
        this.authManager = authenticationManager;
        this.userRepo = userRepo;
    }

    @PostMapping
    public ResponseEntity<UserAndToken> login(@RequestBody LoginPayload payload) {
        if (StringUtils.hasText(payload.getEmail()) && StringUtils.hasText(payload.getPassword())) {
            try {
                Authentication authentication = authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                payload.getEmail(),
                                payload.getPassword()
                        )
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);

                return this.userRepo
                        .findByUsername(payload.getEmail())
                        .map(user -> {
                            UserAndToken userAndToken = new UserAndToken();
                            userAndToken.setToken(jwtProvider.generateToken(user));
                            userAndToken.setUser(userMapper.userToUserDto(user));
                            return ResponseEntity.ok(userAndToken);
                        })
                        .orElseThrow(() -> new UsernameNotFoundException("user not exist"));

            } catch (AuthenticationException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
