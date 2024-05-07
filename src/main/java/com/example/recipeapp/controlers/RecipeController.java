package com.example.recipeapp.controlers;


import com.example.recipeapp.api.RecipeApiHandler;
import com.example.recipeapp.models.Recipe;
import com.example.recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RecipeController {
    private RecipeService recipeService;
    private final RecipeApiHandler recipeApiHandler;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
        this.recipeApiHandler = new RecipeApiHandler();
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Witaj na stronie gotuj z KLASĄ");
        return "index";
    }


    @GetMapping("/view-recipes")
    public String viewRecipes(Model model) {
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

}
