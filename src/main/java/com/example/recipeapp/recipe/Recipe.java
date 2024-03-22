package com.example.recipeapp.recipe;

import java.util.List;
import java.util.stream.Collectors;

public class Recipe {
    private String name;
    private String description;
    private List<String> category;
    private String instructions;
    private List<Ingredient> ingredients;

    public Recipe() {
    }

    public Recipe(String name, String description, List<String> category, String instructions, List<Ingredient> ingredients) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getCategory() {
        return category;
    }

    public String getInstructions() {
        return instructions;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", instructions='" + instructions + '\'' +
                ", ingredients=" + ingredients.stream().map( Ingredient::toString).collect(Collectors.joining(", ")) +
                '}';
    }
}
