package com.example.recipeapp.controlers;


import com.example.recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
//    private final RecipeRepository recipeRepository;
//
//    public RecipeController(RecipeRepository recipeRepository) {
//        this.recipeRepository = recipeRepository;
//    }
//
//    @GetMapping("/recipe/{id}")
//    Recipe findById(@PathVariable Integer id){
//        Optional<Recipe> recipe = recipeRepository.findById(id);
//        if(recipe.isEmpty())
//        {
//            throw new RecipeNotFoundException();
//        }
//        return recipe.get();
//    }
//
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Witaj na stronie gotuj z KLASĄ");
        return "index";
    }
//
////    @GetMapping("/add-recipe")
////    public String addRecipe(Model model) {
////        model.addAttribute("recipe", new Recipe());
////        return "add-recipe";
//
//    @GetMapping("api/recipes")
//    @ResponseBody
//    List<Recipe> findAll() {
//        return recipeRepository.findAll();
//    }
    @GetMapping("/view-recipes")
    public String viewRecipes(Model model) {
        return "view-recipes";
    }
//
//    @GetMapping("/search-recipes")
//    public String showSearchForm() {
//        return "search-recipes"; // Return the search-recipes.html template
//    }

}
