package com.example.documentregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * Класс, представляющий точку входа в приложение для регистрации документов.
 * Аннотация @SpringBootApplication указывает, что класс является точкой входа в Spring-приложение и
 * объединяет в себе следующие три аннотации: @Configuration, @EnableAutoConfiguration, @ComponentScan.
 * Наследуется от SpringBootServletInitializer, чтобы поддерживать развертывание в контейнере сервлетов.
 */
@SpringBootApplication
public class DocumentRegistrationApplication extends SpringBootServletInitializer{
    /**
     * Метод, запускающий приложение с использованием Spring Boot.
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        SpringApplication.run(DocumentRegistrationApplication.class, args);
    }

}
