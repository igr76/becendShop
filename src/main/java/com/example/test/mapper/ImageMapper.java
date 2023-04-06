package com.example.test.mapper;


import com.example.test.dto.ImageDTO;
import com.example.test.entity.ImageEntity;
import org.springframework.web.bind.annotation.Mapping;

/**
 * маппер для {@link ImageEntity} готовый DTO {@link ImageDTO}
 */
@Mapper(componentModel = "spring")
public interface ImageMapper {
  @Mapping(target = "path", source = "image")
  ImageEntity toEntity(ImageDTO imageDTO);
  @Mapping(target = "image", source = "path")
  ImageDTO toDTO(ImageEntity imageEntity);

}
