package com.example.recipeapp.services.impl;

import com.example.recipeapp.models.Recipe;
import com.example.recipeapp.models.User;
import com.example.recipeapp.repositories.RecipeRepository;
import com.example.recipeapp.repositories.UserRepository;
import com.example.recipeapp.security.SecurityUtil;
import com.example.recipeapp.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    public RecipeServiceImpl(RecipeRepository recipeRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    public List<Recipe> findAllUserRecipes(String username){
        return recipeRepository.findByUserUsername(username);
    }
    public void saveRecipe(Recipe recipe){
        String username = SecurityUtil.getSessionUser();
        User user = userRepository.findByUsername(username);
        recipe.setCreator(user);
        recipeRepository.save(recipe);
    }

}
