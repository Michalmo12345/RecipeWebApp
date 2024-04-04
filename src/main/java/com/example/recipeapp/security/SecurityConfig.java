package com.example.recipeapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/login", "/register", "/css/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin((FormLoginConfigurer<HttpSecurity> form) -> form
//                        .loginPage("/login")
//                        .loginProcessingUrl("/login")
//                        .defaultSuccessUrl("/home", true)
//                        .failureUrl("/login?error=true")
//                        .permitAll()
//                )
//                .logout((LogoutConfigurer<HttpSecurity> logout) -> logout
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                        .permitAll()
//                );
//
//        return http.build();
//
//    }
//}
