package com.lana.logon.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Integer id;

    private String email;

    private String avatar_url;

    private String firstName;

    private String lastName;

    private Character gender;
}
