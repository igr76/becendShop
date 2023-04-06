package com.example.test.service.impl;

import com.example.test.dto.Role;
import com.example.test.entity.UserEntity;
import com.example.test.repository.AdsRepository;
import com.example.test.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.example.test.exception.ElemNotFound;
@Service
public class SecurityService {
    AdsRepository adsRepository;

    public SecurityService(AdsRepository adsRepository, UserRepository userRepository) {
        this.adsRepository = adsRepository;
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;

    /** Проверка пользователя на авторство */
    public boolean checkAuthor(int id, UserEntity user) {
        return id == user.getId();
    }
    /** Проверка автора объявления на электронную почту */
    public boolean checkAuthor(int id, String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(ElemNotFound::new);
        return checkAuthor(id, user);
    }
    /** Проверка авторства объявления по Authentication */
    public boolean checkAuthorEmailAndAdsId(int id, Authentication authentication) {
        UserEntity user = userRepository.findByEmail(authentication.getName()).orElseThrow(ElemNotFound::new);
        AdEntity adEntity = adsRepository.findById(id).orElseThrow(ElemNotFound::new);
        return user.getId()==adEntity.getAuthor().getId();
    }
    /** Проверка роли администратора по Authentication */
    public boolean checkAuthorRoleFromAuthentication(Authentication authentication) {
        UserEntity user = userRepository.findByEmail(authentication.getName()).orElseThrow(ElemNotFound::new);
        return isAdmin(user);
    }
    /** Проверка пользователя на электронную почту */
    public boolean isAuthorAuthenticated(String email, Authentication authentication) {
        return authentication.getName().equals(email) && authentication.isAuthenticated();
    }

    public boolean isAuthorAuthenticated(int id, Authentication authentication) {
        UserEntity user = userRepository.findById(id).orElseThrow(ElemNotFound::new);
        return isAuthorAuthenticated(user.getEmail(), authentication);
    }

    public boolean isAuthorAuthenticated(UserEntity user, Authentication authentication) {
        return isAuthorAuthenticated(user.getEmail(), authentication);
    }
    /** Проверка пользователя на роль администратора */
    public boolean isAdmin(UserEntity user) {
        return user.getRole().equals(Role.ADMIN);
    }

    public boolean isAdmin(int id) {
        UserEntity user = userRepository.findById(id).orElseThrow(ElemNotFound::new);
        return isAdmin(user);
    }

    public boolean isAdmin(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(ElemNotFound::new);
        return isAdmin(user);
    }

    public boolean isAdmin(Authentication authentication) {
        return authentication.isAuthenticated() &&
                authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
    /** Проверка законности доступа к методам комментариям */
    public boolean isCommentUpdateAvailable(Authentication authentication, int commentEntityAuthorId,
                                            int commentDTOAuthorId) {
        if (isUpdateAvailable(authentication)) {
            return true;
        }
        if (checkAuthor(commentEntityAuthorId, authentication.getName()) &&
                commentEntityAuthorId == commentDTOAuthorId) {
            return true;
        }
        return false;
    }

    /** Проверка законности доступа к методам объявлений */
    public boolean isAdsUpdateAvailable(Authentication authentication, int adEntityAuthorId) {
        if (isUpdateAvailable(authentication)) {
            return true;
        }
        if (checkAuthor(adEntityAuthorId, authentication.getName())) {
            return true;
        }
        return false;
    }

    /** Проверка возможности обновления */
    private boolean isUpdateAvailable(Authentication authentication) {
        if (!authentication.isAuthenticated()) {
            return false;
        }
        if (isAdmin(authentication)) {
            return true;
        }
        return false;
    }
}
