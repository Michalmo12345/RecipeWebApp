package com.example.recipeapp.run;

import java.util.Map;

public class Ingredient {
    private String name;
    private String amount;

    private Integer recipe_id;

    private Map<String, Float> nutrition;

    public Ingredient() {
    }

    public Ingredient(String name, String amount, Integer recipe_id) {
        this.name = name;
        this.amount = amount;
        this.recipe_id = recipe_id;
    }


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

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setRecipe_id(Integer recipe_id) {
        this.recipe_id = recipe_id;
    }

    public void setNutrition(Map<String, Float> nutrition) {
        this.nutrition = nutrition;
    }

    public String toString() {
        return "'" + name + "'" + " " + amount;
    }
}
