package com.example.recipeapp.models;

import com.example.recipeapp.models.Ingredient;
import com.example.recipeapp.models.Recipe;
import com.example.recipeapp.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    private Recipe recipe;
    private User user;
    private List<Ingredient> ingredients;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");

        Ingredient ingredient1 = new Ingredient("Flour", 100.0);
        Ingredient ingredient2 = new Ingredient("Eggs", 2.0);
        Ingredient ingredient3 = new Ingredient("Milk", 200.0);
        Ingredient ingredient4 = new Ingredient("Sugar", 50.0);

        ingredients = Arrays.asList(ingredient1, ingredient2, ingredient3, ingredient4);

        recipe = new Recipe("Pancakes", "Breakfast", "Mix ingredients and cook on a griddle.", 15, ingredients);
        recipe.setId(1);
        recipe.setCreator(user);

        for (Ingredient ingredient : ingredients) {
            ingredient.setRecipe(recipe);
        }
    }

    @Test
    void setCreator() {
        User newUser = new User();
        newUser.setUsername("Jane Doe");
        newUser.setEmail("jane.doe@example.com");
        newUser.setPassword("password456");

        recipe.setCreator(newUser);
        assertEquals(newUser, recipe.getUser());
    }

    @Test
    void getName() {
        assertEquals("Pancakes", recipe.getName());
    }

    @Test
    void getInstructions() {
        assertEquals("Mix ingredients and cook on a griddle.", recipe.getInstructions());
    }

    @Test
    void getTime() {
        assertEquals(15, recipe.getTime());
    }

    @Test
    void getId() {
        assertEquals(1, recipe.getId());
    }

    @Test
    void getCategory() {
        assertEquals("Breakfast", recipe.getCategory());
    }

    @Test
    void setId() {
        recipe.setId(2);
        assertEquals(2, recipe.getId());
    }

    @Test
    void testToString() {
        String expectedString = "Pancakes;Breakfast;Mix ingredients and cook on a griddle.;15;Flour!100.0#Eggs!2.0#Milk!200.0#Sugar!50.0#";
        assertEquals(expectedString, recipe.toString());
    }

    @Test
    void getIngredients() {
        assertEquals(ingredients, recipe.getIngredients());
    }
}