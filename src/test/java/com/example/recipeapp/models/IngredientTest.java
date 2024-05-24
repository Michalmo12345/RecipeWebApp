package com.example.recipeapp.models;

import com.example.recipeapp.models.Ingredient;
import com.example.recipeapp.models.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    private Ingredient ingredient;
    private Recipe recipe;

    @BeforeEach
    void setUp() {
        ingredient = new Ingredient("Sugar", 50.0);
        recipe = new Recipe();
    }

    @Test
    void getName() {
        assertEquals("Sugar", ingredient.getName());
    }

    @Test
    void getWeight() {
        assertEquals(50.0, ingredient.getWeight());
    }

    @Test
    void setName() {
        ingredient.setName("Flour");
        assertEquals("Flour", ingredient.getName());
    }

    @Test
    void setWeight() {
        ingredient.setWeight(100.0);
        assertEquals(100.0, ingredient.getWeight());
    }

    @Test
    void testToString() {
        assertEquals("Sugar!50.0", ingredient.toString());
    }

    @Test
    void getRecipe() {
        ingredient.setRecipe(recipe);
        assertEquals(recipe, ingredient.getRecipe());
    }

    @Test
    void setRecipe() {
        ingredient.setRecipe(recipe);
        assertEquals(recipe, ingredient.getRecipe());
    }
}
