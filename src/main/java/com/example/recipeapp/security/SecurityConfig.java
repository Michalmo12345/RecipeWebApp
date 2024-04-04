package com.example.recipeapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF for simplicity in this example, consider enabling it for production
                .csrf().disable()
                .authorizeHttpRequests(authz -> authz
                        // Permit all access to the register page
                        .requestMatchers("/register", "/css/**", "/js/**").permitAll()
                        // Any other request must be authenticated
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        // Specify the login page URL, Spring Security will allow unauthenticated access
                        .loginPage("/login")
                        .permitAll() // Allow all users to access the login page
                )
                .logout(logout -> logout.permitAll()); // Allow all users to logout
        return http.build();
    }
}
