package com.example.recipeapp;

public class Recipe {
    private String name;
    private Integer total_calories;
    private String id;
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Recipe() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotal_calories() {
        return total_calories;
    }

    public void setTotal_calories(Integer total_calories) {
        this.total_calories = total_calories;
    }
    public Recipe(String name, String id, Integer total_calories)
    {
        this.name = name;
        this.id = id;
        this.total_calories = total_calories;
    }
}
