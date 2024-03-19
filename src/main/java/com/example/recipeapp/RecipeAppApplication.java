package com.example.recipeapp;

import com.example.recipeapp.run.Recipe;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@SpringBootApplication
public class RecipeAppApplication {
    private static final Logger log = LoggerFactory.getLogger(RecipeAppApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(RecipeAppApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(){
        return args -> {
            Recipe recipe = new Recipe(1, "Pasta", "Pasta with tomato sauce",500);
            log.info("Recipes: " + recipe);
        };
    }
}
