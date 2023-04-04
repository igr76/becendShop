package com.example.test.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.skypro.homework.entity.AdEntity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * A DTO for the {@link AdEntity} entity
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdsDTO {

  Integer author;
  String image;
  Integer pk;
  @NotNull
  @Min(value = 1)
  Integer price;
  @NotNull(message = "Обязательно нужно заполнить поле")
  String title;
}