package com.example.recipeapp.services;


import com.example.recipeapp.models.Recipe;
import com.example.recipeapp.models.User;
import com.example.recipeapp.repositories.RecipeRepository;
import com.example.recipeapp.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }
    public void saveRecipe(Recipe recipe){
        recipeRepository.save(recipe);
    }
}
