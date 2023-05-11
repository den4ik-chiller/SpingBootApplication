package com.example.documentregistration.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, реализующий интерфейс UserDetails и представляющий информацию о пользователе.
 * Класс используется для аутентификации и авторизации пользователей.
 */
public class UserInfoUserDetails implements UserDetails {
    /**
     * Поле, содержащее имя пользователя
     */
    private String name;

    /**
     * Поле, содержащее пароль пользователя
     */
    private String password;

    /**
     * Поле, содержащее список ролей пользователя
     */
    private List<GrantedAuthority> authorities;


    /**
     * Конструктор класса UserInfoUserDetails, который инициализирует поля объекта
     * информацией о пользователе из объекта класса UserInfo.
     * @param userInfo Объект класса UserInfo, содержащий информацию о пользователе.
     */
    public UserInfoUserDetails(UserInfo userInfo) {
        name = userInfo.getName();
        password = userInfo.getPassword();
        authorities = Arrays.stream(userInfo.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * Метод, возвращающий список прав доступа, связанных с пользователем.
     * @return Список прав доступа, связанных с пользователем.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Метод, возвращающий пароль пользователя.
     * @return Пароль пользователя.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Метод, возвращающий имя пользователя.
     * @return Имя пользователя.
     */
    @Override
    public String getUsername() {
        return name;
    }

    /**
     * Метод, указывающий, истек ли срок действия учетной записи пользователя.
     * @return true, если учетная запись пользователя действительна (не истекла), иначе - false.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Метод, указывающий, заблокирована ли учетная запись пользователя.
     * @return true, если учетная запись пользователя не заблокирована, иначе - false.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Метод, указывающий, истек ли срок действия учетных данных пользователя.
     * @return true, если учетные данные пользователя действительны (не истекли), иначе - false.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Метод, указывающий, включен ли пользователь.
     * @return true, если пользователь включен, иначе - false.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
