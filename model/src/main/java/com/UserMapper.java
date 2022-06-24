package com;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "dob", target = "dob", dateFormat = "dd/MM/yyyy")
    UserDTO toDto(User user);

    @Mapping(source = "dob", target = "dob", dateFormat = "dd/MM/yyyy")
    User toEntity(UserDTO user);

    @InheritConfiguration
    @Mapping(target = "email",ignore = true)
    void updateEntity(UserDTO userDTO, @MappingTarget User user);
}
