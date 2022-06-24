package com;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "dob", target = "dob", dateFormat = "dd/MM/yyyy")
    UserDTO toDto(User user);

    @InheritInverseConfiguration
    User toEntity(UserDTO user);

    @InheritConfiguration
    @Mapping(target = "email",ignore = true)
    void updateEntity(UserDTO userDTO, @MappingTarget User user);
}
