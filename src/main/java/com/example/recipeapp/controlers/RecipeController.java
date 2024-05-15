package com.example.recipeapp.controlers;


import com.example.recipeapp.api.RecipeApiHandler;
import com.example.recipeapp.models.Ingredient;
import com.example.recipeapp.models.Recipe;
import com.example.recipeapp.repositories.RecipeRepository;
import com.example.recipeapp.security.SecurityUtil;
import com.example.recipeapp.services.RecipeService;
import com.example.recipeapp.services.UserService;
import com.example.recipeapp.models.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RecipeController {
    private RecipeService recipeService;
    private UserService userService;
    private final RecipeApiHandler recipeApiHandler;

    public RecipeController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
        this.recipeApiHandler = new RecipeApiHandler();
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Witaj na stronie gotuj z KLASĄ");
        return "index";
    }


    @GetMapping("/view-recipes")
    public String viewRecipes(Model model) {

        String username = SecurityUtil.getSessionUser();
        List<Recipe> recipes = recipeService.findAllUserRecipes(username);
        model.addAttribute("recipes", recipes);
        return "view-recipes";
    }

    @GetMapping("/search-recipes")
    public String searchRecipes(@RequestParam(required = false) String query, Model model) {
        List<Recipe> recipes;
        if (query != null && !query.isEmpty()) {
            recipes = recipeApiHandler.getRecipes(query);
        } else {
            recipes = List.of(); // or provide some default list of recipes
        }
        model.addAttribute("recipes", recipes);
        return "api-recipes"; // This is the name of the Thymeleaf template (e.g., recipes.html under src/main/resources/templates)
    }

    @PostMapping("/add-recipe")
    public String addRecipe(@RequestParam("recipe") String recipeString) {
        Recipe recipe = recipeApiHandler.convertFromString(recipeString);
        recipeService.saveRecipe(recipe);
        return "redirect:/search-recipes";
    }

    @GetMapping("/add-own-recipe")
    public String addOwnRecipe(@RequestParam("recipe") String recipeString) {
        Recipe recipe = recipeApiHandler.convertFromString(recipeString);
        recipeService.saveRecipe(recipe);
        return "add-own-recipe";
    }

    @PostMapping("/view-recipe/{recipeId}/delete")
    public String deleteRecipe(@PathVariable Integer recipeId) {
        recipeService.deleteRecipe(recipeId);
        return "redirect:/view-recipes";
    }
}
