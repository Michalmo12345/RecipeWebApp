package com.example.recipeapp.run;

import java.util.Map;

public class Ingredient {
    private String name;
    private String amount;

    private Map<String, Float> nutrition;

    public Ingredient() {
    }

    public Ingredient(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public Map<String, Float> getNutrition() {
        return nutrition;
    }

    public String toString() {
        return "'" + name + "'" + " " + amount;
    }
}
