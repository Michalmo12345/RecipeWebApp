package com.example.recipeapp.run;

import java.util.Map;

public class Ingredient {
    private String name;
    private String amount;

    private Map<String, Double> nutrition;

    public Ingredient() {
    }

    public Ingredient(String name, String amount, Map<String, Double> nutrition) {
        this.name = name;
        this.amount = amount;
        this.nutrition = nutrition;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public Map<String, Double> getNutrition() {
        return nutrition;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Double> entry : nutrition.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
        }
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                ", nutrition=" + sb.toString() +
                '}';
    }
}
