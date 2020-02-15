package com.lana.logon.util.mapper;

import com.lana.logon.dto.user.UserDto;
import com.lana.logon.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "avatar", source = "avatarUrl")
    @Mapping(target = "email", source = "username")
    UserDto userToUserDto(User user);
}
