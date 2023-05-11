package com.example.documentregistration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Класс, реализующий интерфейс UserDetailsService, для загрузки информации о пользователе из базы данных.
 */
@Component
public class UserInfoDetailsService implements UserDetailsService {
    /**
     * Репозиторий для доступа к информации о пользователях.
     */
    @Autowired
    private UserInfoRepository repo;

    /**
     * Метод для загрузки информации о пользователе по имени пользователя.
     * @param username имя пользователя.
     * @return UserDetails объект, содержащий информацию о пользователе.
     * @throws UsernameNotFoundException если пользователь не найден в базе данных.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repo.findByName(username);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found" + username));
    }
}
