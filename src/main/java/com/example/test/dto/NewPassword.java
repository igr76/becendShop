package com.example.test.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * DTO пароля
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewPassword {

  /**
   * id пароля
   */
  @JsonIgnore
  private Long id;
  /**
   * текущий пароль
   */
  String currentPassword;
  /**
   * новый пароль
   */
  String newPassword;

}
