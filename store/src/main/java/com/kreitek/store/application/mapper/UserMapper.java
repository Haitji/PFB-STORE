package com.kreitek.store.application.mapper;


import com.kreitek.store.application.dto.UserDTO;
import com.kreitek.store.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses={ItemMapper.class,CategoryMapper.class})
public interface UserMapper extends EntityMapper<UserDTO, User> {
    @Override
    @Mapping(target = "carritoItems",ignore = true)
    User toEntity(UserDTO user);

    @Override
    @Mapping(target = "carritoItems",ignore = true)
    List<User> toEntity(List<UserDTO> user);
}
