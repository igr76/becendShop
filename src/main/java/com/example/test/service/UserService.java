package com.example.test.service;

import com.example.test.dto.NewPassword;
import com.example.test.dto.UserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

/**
 * сервис пользователя
 */
public interface UserService {

  /**
   * получить пользователя
   */
  UserDTO getUser(Authentication authentication);

  /**
   * обновить пользователя
   */
  UserDTO updateUser(UserDTO userDto, Authentication authentication) ;

  /**
   * установить новый пароль пользователя
   */
  NewPassword setPassword(NewPassword newPassword);

  /**
   * обновить фото пользователя
   */
  void updateUserImage(MultipartFile image, Authentication authentication);


  /**
   * получить фото пользователя
   */
  byte[] getPhotoById(Integer id);
}
