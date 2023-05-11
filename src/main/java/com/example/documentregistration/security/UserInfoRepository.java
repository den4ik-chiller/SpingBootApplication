package com.example.documentregistration.security;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Интерфейс для доступа к информации о пользователях в базе данных.
 */
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {

    /**
     * Метод для поиска пользователя по имени.
     * @param username имя пользователя.
     * @return Optional объект, содержащий найденную информацию о пользователе.
     */
    Optional<UserInfo> findByName(String username);

}
