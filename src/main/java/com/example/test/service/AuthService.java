package com.example.test.service;


import com.example.test.dto.RegisterReq;
import com.example.test.dto.Role;

/** Сервис для регистрации */
public interface AuthService {
    /** Логин и пароль пользователя */
    boolean login(String userName, String password);
    /** регистрация и роль пользователя */
    boolean register(RegisterReq registerReq, Role role);
}
