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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredients;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    public Recipe() {}

//    public Recipe() {
//    }

    public void setCreator(User user){
        this.user = user;
    }
    public Recipe(Integer id, String name, String category, String instructions, int time, List<Ingredient> ingredients) {
        this.id = id;
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
        return id + "," +
                name + "," +
                category + "," +
                instructions + "," +
//                ", ingredients=" + ingredients.stream().map(Ingredient::toString).collect(Collectors.joining(", ")) + "\n" +
                time;
    }
}