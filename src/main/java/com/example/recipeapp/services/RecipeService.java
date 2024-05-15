package com.example.recipeapp.services;


import com.example.recipeapp.models.Recipe;

import java.util.List;


public interface RecipeService {
    List<Recipe> findAllUserRecipes(String username);
    void saveRecipe(Recipe recipe);

    void deleteRecipe(Integer id);
}