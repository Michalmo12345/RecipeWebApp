package com.example.recipeapp;

import com.example.recipeapp.run.Ingredient;
import com.example.recipeapp.run.Recipe;
import com.example.recipeapp.api.RecipeApiHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;
@SpringBootApplication
public class RecipeAppApplication {

    private static final Logger log = LoggerFactory.getLogger(RecipeAppApplication.class);
    public static void main(String[] args) {

        SpringApplication.run(RecipeAppApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            RecipeApiHandler recipeApiHandler = new RecipeApiHandler();
            String recipeInfo = recipeApiHandler.getRecipes("chicken").toString();
            log.info(recipeInfo);
        };
    }
}
