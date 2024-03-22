package com.example.recipeapp.run;


import com.example.recipeapp.run.Recipe;
import com.example.recipeapp.run.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    private final RecipeRepository recipeRepository;

    public HomeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/api/recipes/{id}")
    Recipe findById(@PathVariable Integer id){
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(recipe.isEmpty())
        {
            throw new RecipeNotFoundException();
        }
        return recipe.get();
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Witaj na stronie gotuj z KLASĄ");
        return "index";
    }

//    @GetMapping("/add-recipe")
//    public String addRecipe(Model model) {
//        model.addAttribute("recipe", new Recipe());
//        return "add-recipe";

    @GetMapping("api/recipes")
    @ResponseBody
    List<Recipe> findAll() {
        return recipeRepository.findAll();
    }
    @GetMapping("/view-recipes")
    public String viewRecipes(Model model) {
        model.addAttribute("recipes", recipeRepository.findAll());
        return "view-recipes";
    }

//    @GetMapping("/search-recipes")
//    public String searchRecipes(Model model) {
//        model.addAttribute("recipes", recipeRepository.findById());
//        return "search-recipes";
//    }


}
