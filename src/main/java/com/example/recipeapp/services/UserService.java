package com.example.recipeapp.services;

import com.example.recipeapp.dto.RegistrationDto;
import com.example.recipeapp.models.User;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);

    User findByUsername(String username);
}
