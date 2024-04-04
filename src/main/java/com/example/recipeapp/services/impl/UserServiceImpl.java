package com.example.recipeapp.services.impl;

import com.example.recipeapp.dto.RegistrationDto;
import com.example.recipeapp.models.User;
import com.example.recipeapp.repositories.UserRepository;
import com.example.recipeapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;



    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(registrationDto.getPassword());
        userRepository.save(user);
    }
}
