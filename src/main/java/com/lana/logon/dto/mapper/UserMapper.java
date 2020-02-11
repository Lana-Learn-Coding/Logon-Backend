package com.lana.logon.dto.mapper;

import com.lana.logon.dto.UserDto;
import com.lana.logon.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "avatar", source = "avatarUrl")
    UserDto userToUserDto(User user);
}
