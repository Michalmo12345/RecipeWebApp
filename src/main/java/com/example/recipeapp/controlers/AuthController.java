package com.example.recipeapp.controlers;



import com.example.recipeapp.RecipeAppApplication;
import com.example.recipeapp.dto.RegistrationDto;
import com.example.recipeapp.models.User;
import com.example.recipeapp.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private UserService userService;
    private static final Logger log = LoggerFactory.getLogger(RecipeAppApplication.class);

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }


    @GetMapping("/register")
    public String getRegisterForm(Model model){
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user,
                           BindingResult result, Model model) {
        User existingUserEmail = userService.findByEmail(user.getEmail());
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            result.rejectValue("email", "error.user", "There is already a user with this email");
        }
        User existingUsername = userService.findByUsername(user.getUsername());
        if(existingUsername != null && existingUsername.getUsername() != null && !existingUsername.getUsername().isEmpty()) {
            result.rejectValue("username", "error.user", "There is already a user with this username");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register"; // Make sure this view name matches your Thymeleaf template name
        }
        userService.saveUser(user);
        return "redirect:/";
    }
}
