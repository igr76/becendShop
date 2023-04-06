package com.example.test.mapper;


import com.example.test.dto.CreateAds;
import com.example.test.dto.FullAds;
import com.example.test.entity.AdEntity;
import com.example.test.entity.ImageEntity;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;
import java.util.stream.Collectors;
/**
 * дополнительный маппер для объявлений
 */
@Mapper(componentModel = "spring", uses = {ImageMapper.class})
public interface AdsOtherMapper {


  @Mapping(target = "description", source = "description")
  @Mapping(target = "price", source = "price")
  @Mapping(target = "title", source = "title")
  CreateAds toCreateAds(AdEntity adEntity);

  @Mapping(target = "pk", source = "id")
  @Mapping(target = "authorFirstName", source = "author.firstName")
  @Mapping(target = "authorLastName", source = "author.lastName")
  @Mapping(target = "description", source = "description")
  @Mapping(target = "email", source = "author.email")
  @Mapping(target = "image", expression = "java(setImage(adEntity.getImageEntities()))")
  @Mapping(target = "phone", source = "author.phone")
  @Mapping(target = "price", source = "price")
  @Mapping(target = "title", source = "title")
  FullAds toFullAds(AdEntity adEntity);

  default List<String> setImage(List<ImageEntity> imageEntities) {
    if (imageEntities == null || imageEntities.size() == 0) {
      return null;
    }
    return imageEntities
        .stream()
        .map(ImageEntity::getPath)
        .collect(Collectors.toList());
  }

}
