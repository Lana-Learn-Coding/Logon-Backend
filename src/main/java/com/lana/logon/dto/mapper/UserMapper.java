package com.lana.logon.dto.mapper;

import com.lana.logon.dto.UserDto;
import com.lana.logon.model.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto(User user);
}
