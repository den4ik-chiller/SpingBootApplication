package com.example.documentregistration.security;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность, представляющая информацию о пользователе, хранящуюся в базе данных.
 * <p>
 * Класс содержит поля для идентификации пользователя, его имени, пароля и ролей.
 * <p>
 * Используется аннотация {@link Entity} для указания, что объект является сущностью JPA.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    /**
     * Идентификатор пользователя.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    /**
     * Имя пользователя.
     */
    private String name;
    /**
     * Пароль пользователя.
     */
    private String password;
    /**
     * Роли пользователя, разделенные запятыми.
     */
    private String roles;
    /**
     * Возвращает пароль пользователя.
     *
     * @return пароль пользователя
     */
    public String getPassword(){
        return password;
    }
    /**
     * Возвращает имя пользователя.
     *
     * @return имя пользователя
     */
    public String getName(){
        return name;
    }

}
