package com.example.recipeapp.controlers;

import com.example.recipeapp.models.User;
import com.example.recipeapp.repositories.UserRepository;
import com.example.recipeapp.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/profile")
    public String userProfile(Model model) {
        // Dodac logikę sprawdzania, czy użytkownik jest zalogowany
        String username = SecurityUtil.getSessionUser();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);
        return "profile";
    }
}
