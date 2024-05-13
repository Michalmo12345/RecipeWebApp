package com.example.recipeapp.services;


import com.example.recipeapp.models.Recipe;

import java.util.List;

//@Service
//public class RecipeService {
//
//    private final RecipeRepository recipeRepository;
//    private final UserRepository userRepository;
//    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository) {
//        this.recipeRepository = recipeRepository;
//        this.userRepository = userRepository;
//    }
//    public void saveRecipe(Recipe recipe){
//        recipeRepository.save(recipe);
//    }
//}
public interface RecipeService {
    List<Recipe> findAllRecipes();
    void saveRecipe(Recipe recipe);
}