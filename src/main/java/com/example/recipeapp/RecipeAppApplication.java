package com.example.recipeapp;

import com.example.recipeapp.models.Ingredient;
import com.example.recipeapp.models.Recipe;

import com.example.recipeapp.repositories.RecipeRepository;
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

}