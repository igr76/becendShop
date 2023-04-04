package com.example.test.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAds {

    /**
     * Описание продаваемого объекта
     */
    String description;

    /**
     * цена
     */
    @Min(1)
    Integer price;

    /**
     * Название
     */
    String title;


}
