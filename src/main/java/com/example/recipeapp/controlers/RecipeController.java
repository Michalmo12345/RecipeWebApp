package com.example.recipeapp.controlers;


import com.example.recipeapp.api.RecipeApiHandler;
import com.example.recipeapp.export.CsvExporter;
import com.example.recipeapp.models.Ingredient;
import com.example.recipeapp.models.Recipe;
import com.example.recipeapp.repositories.RecipeRepository;
import com.example.recipeapp.security.SecurityUtil;
import com.example.recipeapp.services.RecipeService;
import com.example.recipeapp.services.UserService;
import com.example.recipeapp.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/add-own-recipe")
    public String addOwnRecipe(Model model) {
        return "add-own-recipe";
    }

    @GetMapping("/search-recipes")
    public String searchRecipes(@RequestParam(required = false) String query, Model model) {
        List<Recipe> recipes;
        if (query != null && !query.isEmpty()) {
            recipes = recipeApiHandler.getRecipes(query);
        } else {
            recipes = List.of();
        }
        model.addAttribute("recipes", recipes);
        return "api-recipes";
    }

    @PostMapping("/add-recipe")
    public String addRecipe(@RequestParam("recipe") String recipeString) {
        Recipe recipe = recipeApiHandler.convertFromString(recipeString);
        recipeService.saveRecipe(recipe);
        return "redirect:/view-recipes";
    }

    @PostMapping("/add-own-recipe")
    public String addOwnRecipe(@RequestParam("recipe") String recipeString) {
        Recipe recipe = recipeApiHandler.convertFromString(recipeString);
        recipeService.saveRecipe(recipe);
        return "redirect:/view-recipes";
    }

    @PostMapping("/view-recipe/{recipeId}/delete")
    public String deleteRecipe(@PathVariable Integer recipeId) {
        recipeService.deleteRecipe(recipeId);
        return "redirect:/view-recipes";
    }

    @GetMapping("/user/export-recipes")
    public ResponseEntity<?> exportRecipes(@RequestParam(required = false) String filename,
                                           @RequestParam(defaultValue = "csv") String format) {
        String username = SecurityUtil.getSessionUser();
        List<Recipe> recipes = recipeService.findAllUserRecipes(username);

        HttpHeaders headers = new HttpHeaders();
        if (filename == null || filename.trim().isEmpty()) {
            filename = "my_recipes"; // default filename without extension here
        }

        switch (format.toLowerCase()) {
            case "json":
                ObjectMapper mapper = new ObjectMapper();
                String json;
                try {
                    json = mapper.writeValueAsString(recipes);
                } catch (Exception e) {
                    return ResponseEntity.internalServerError().body("Error generating JSON");
                }
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + ".json\"");
                headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
                return ResponseEntity.ok().headers(headers).body(json);

            case "txt":
                StringBuilder sb = new StringBuilder();
                for (Recipe recipe : recipes) {
                    sb.append("Name: ").append(recipe.getName()).append("\n");
//                    sb.append("Ingredients: ").append(recipe.getIngredients()).append("\n");
                    sb.append("Category: ").append(recipe.getCategory()).append("\n");
                    sb.append("Instructions: ").append(recipe.getInstructions()).append("\n\n");
                }
                String textData = sb.toString();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + ".txt\"");
                headers.add(HttpHeaders.CONTENT_TYPE, "text/plain");
                return ResponseEntity.ok().headers(headers).body(textData);

            case "csv":
            default:
                String csvData = CsvExporter.convertToCSV(recipes);
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + ".csv\"");
                headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");
                return ResponseEntity.ok().headers(headers).body(csvData);
        }
    }
}
