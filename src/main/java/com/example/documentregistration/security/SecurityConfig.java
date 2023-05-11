package com.example.documentregistration.security;

import com.example.documentregistration.CustomAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Конфигурационный класс для Spring Security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Возвращает пользовательскую информацию сервиса.
     *
     * @return сервис пользовательской информации.
     */
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserInfoDetailsService();
    }

    /**
     * Возвращает объект кодировщика паролей BCrypt.
     *
     * @return объект кодировщика паролей.
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Возвращает цепочку фильтров безопасности, используемую для настройки доступа к различным URL-адресам веб-приложения.
     *
     * @param http объект HttpSecurity для настройки фильтра безопасности.
     * @return цепочку фильтров безопасности.
     * @throws Exception если произошла ошибка при настройке цепочки фильтров.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/reg").permitAll()
                .requestMatchers("/reg_admin").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/new").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/edit/{id}").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/delete/{id}").hasAuthority("ROLE_ADMIN")
                .and()
                .authorizeHttpRequests().requestMatchers("/**").authenticated()
                .and().formLogin().loginPage("/login_page").defaultSuccessUrl("/").permitAll()
                .and()
                .exceptionHandling()
                //начало настройки обработки исключений при выполнении запросов
                .accessDeniedHandler(accessDeniedHandler())
                .and()
                .logout()
                .logoutSuccessUrl("/login_page")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and().build();
    }

    /**
     * Создает бин для провайдера аутентификации, используемого для проверки учетных записей пользователей.
     *
     * @return провайдер аутентификации {@link AuthenticationProvider}
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService((userDetailsService()));
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Создает бин для обработчика доступа, используемого для обработки ошибок доступа.
     *
     * @return обработчик доступа {@link AccessDeniedHandler}
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
}
