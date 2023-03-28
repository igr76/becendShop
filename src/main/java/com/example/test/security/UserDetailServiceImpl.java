package com.example.test.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.skypro.homework.WebSecurityConfig;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.ElemNotFound;
import ru.skypro.homework.loger.FormLogInfo;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.utils.Encoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("UserDetailServiceImpl")
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Аутентификация пользователя
   */
  public boolean login(String email, String password) {
    log.info(FormLogInfo.getInfo());
    UserEntity userEntity = userRepository.findByEmail(email).orElse(null);
    if (userEntity == null) {
      return false;
    }
    String encryptedPassword = userEntity.getPassword();
    return Encoder.passwordEncoderWithBCrypt().matches(password, encryptedPassword);
  }

  /**
   * Сетим поля ентити из запроса для сохранения в таблицу, проверяем на нуль
   */
  public boolean register(RegisterReq registerReq) {
    log.info(FormLogInfo.getInfo());
    UserEntity userEntity = userRepository.findByEmail(registerReq.getUsername()).orElse(null);
    if (userEntity != null) {
      return false;
    }
    userEntity = new UserEntity();
    userEntity.setFirstName(registerReq.getFirstName());
    userEntity.setLastName(registerReq.getLastName());
    userEntity.setPhone(registerReq.getPhone());
    userEntity.setRegDate(LocalDateTime.now());
    userEntity.setEmail(registerReq.getUsername());
    userEntity.setCity("WithBlackJackAndWhore");
    userEntity.setImage(
        "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");
    userEntity.setPassword(Encoder.passwordEncoderWithBCrypt().encode(registerReq.getPassword()));
    userEntity.setRole(Role.USER);
    userRepository.save(userEntity);
    return true;
  }

  /**
   * передаем пароль и почту для настройки {@link WebSecurityConfig #daoAuthenticationProvider() }
   */
  public UserDetails loadUserByUsername(String email) {
    UserEntity userEntity = userRepository.findByEmail(email)
        .orElseThrow(ElemNotFound::new);
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    if (userEntity.getRole().equals(Role.ADMIN)) {
      authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
  }


}