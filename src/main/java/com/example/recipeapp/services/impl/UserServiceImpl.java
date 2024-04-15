package com.example.recipeapp.services.impl;

import com.example.recipeapp.RecipeAppApplication;
import com.example.recipeapp.dto.RegistrationDto;
import com.example.recipeapp.models.User;
import com.example.recipeapp.repositories.UserRepository;
import com.example.recipeapp.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;
    private static final Logger log = LoggerFactory.getLogger(RecipeAppApplication.class);


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        log.info("User saved successfully");
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


//    @Override
//    public UserDetailsService userDetailsService(){
//        return new UserDetailsService(){
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
//                return userRepository.findByUsername(username);
//            }
//        };
//    }


}
