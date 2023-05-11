package com.example.documentregistration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Класс конфигурации MVC. Реализует интерфейс WebMvcConfigurer
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer{

    /**
     * Пустой метод для регистрации view контроллеров в реестре.
     * Необходим для корректного функционирования MVC.
     * @param registry - реестр контроллеров
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry){}
}

