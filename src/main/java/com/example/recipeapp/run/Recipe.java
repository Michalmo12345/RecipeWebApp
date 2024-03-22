package com.example.recipeapp.run;

import com.example.recipeapp.run.Ingredient;

import java.util.List;
import java.util.stream.Collectors;

public class Recipe {

    public Integer id;
    private String name;
    private String description;
    private List<String> category;
    private String instructions;
    private List<Ingredient> ingredients;

    public Recipe() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Recipe(Integer id, String name, String description, List<String> category, String instructions, List<Ingredient> ingredients) {
        this.id = id;
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
                "id=" + id +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", instructions='" + instructions + '\'' +
                ", ingredients=" + ingredients.stream().map( Ingredient::toString).collect(Collectors.joining(", ")) +
                '}';
    }
}