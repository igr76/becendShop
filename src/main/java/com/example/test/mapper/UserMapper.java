package com.example.test.mapper;


import com.example.test.dto.UserDTO;
import com.example.test.entity.UserEntity;
import org.springframework.web.bind.annotation.Mapping;

import java.util.Collection;

/**
 * маппер для {@link UserEntity} готовый dto {@link UserDTO}
 */

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "regDate", source = "regDate", dateFormat = "dd-MM-yyyy HH:mm:ss")
  @Mapping(target = "adEntities", ignore = true)
  @Mapping(target = "commentEntities", ignore = true)
  UserEntity toEntity(UserDTO userDto);

  @Mapping(target = "regDate", source = "regDate", dateFormat = "dd-MM-yyyy HH:mm:ss")
  UserDTO toDTO(UserEntity userEntity);

  Collection<UserEntity> toEntityList(Collection<UserDTO> userDTOS);

  Collection<UserDTO> toDTOList(Collection<UserEntity> userEntities);
}
