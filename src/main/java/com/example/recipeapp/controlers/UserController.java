package com.example.recipeapp.controlers;

import com.example.recipeapp.models.Recipe;
import com.example.recipeapp.models.User;
import com.example.recipeapp.repositories.UserRepository;
import com.example.recipeapp.security.SecurityUtil;
import com.example.recipeapp.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    private final UserRepository userRepository;
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/calorie-calculator")
    public String showCalorieCalculator() {
        return "calorie-calculator";
    }


    @PostMapping("/calculate-calories")
    public String calculateCalories(@RequestParam int age, @RequestParam int weight, @RequestParam int height, @RequestParam String gender, Model model) {
        double bmr = calculateBMR(age, weight, height, gender);
        model.addAttribute("calories", bmr);
        return "calorie-results";
    }
    @GetMapping("/user/profile")
    public String userProfile(Model model) {
        String username = SecurityUtil.getSessionUser();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);
        return "profile";
    }


    private double calculateBMR(int age, int weight, int height, String gender) {
        if (gender.equals("male")) {
            return 10 * weight + 6.25 * height - 5 * age + 5;
        } else {
            return 10 * weight + 6.25 * height - 5 * age - 161;
        }
    }

}
