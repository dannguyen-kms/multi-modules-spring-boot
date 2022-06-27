package com;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/** Mapper convert User Entity to User DTO and reverse. */
@Mapper(componentModel = "spring")
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Mapping(source = "dob", target = "dob", dateFormat = "dd/MM/yyyy")
  UserDto toDto(User user);

  @InheritInverseConfiguration
  User toEntity(UserDto userDto);

  @InheritConfiguration
  @Mapping(target = "email", ignore = true)
  void updateEntity(UserDto userDto, @MappingTarget User user);
}
