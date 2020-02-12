package com.lana.logon.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAndToken {
    private UserDto user;
    private String token;
}
