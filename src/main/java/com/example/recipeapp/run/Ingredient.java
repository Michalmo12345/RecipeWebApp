package com.example.recipeapp.run;

import java.util.Map;

public class Ingredient {
    private Integer id;
    private String name;
    private String amount;

    private Integer recipe_id;

    private Map<String, Float> nutrition;

    public Ingredient() {
    }

    public Ingredient(Integer id, String name, String amount, Integer recipe_id) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.recipe_id = recipe_id;
    }

    public Integer getId(){return id;}
    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }
    public Integer getRecipe_id(){return recipe_id;}
    public Map<String, Float> getNutrition() {
        return nutrition;
    }

    public String toString() {
        return "'" + name + "'" + " " + amount;
    }
}
