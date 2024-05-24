package com.example.recipeapp.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "recipes")

public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    private String name;
    private String category;
    private String instructions;
    private int time;
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredients;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    public Recipe() {}
    public void setCreator(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }
    public Recipe(String name, String category, String instructions, int time, List<Ingredient> ingredients) {
        this.name = name;
        this.category = category;
        this.instructions = instructions;
        this.time = time;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }


    public String getInstructions() {
        return instructions;
    }


    public int getTime() {
        return time;
    }

    public Integer getId() {
        return id;
    }

    public String getCategory() {return category;}

    public void setId(Integer id) {
        this.id = id;
    }

    public String toString() {
        String ingredientsString = "";
        for (Ingredient ingredient : ingredients) {
            ingredientsString += ingredient.toString() + "#";
        }
        return name + ";" +
                category + ";" +
                instructions + ";" +
                time + ";" +
                ingredientsString;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}