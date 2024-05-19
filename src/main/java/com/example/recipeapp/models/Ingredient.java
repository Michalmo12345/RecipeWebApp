package com.example.recipeapp.models;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    private String name;
    private Double weight;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    public Ingredient() {
    }

    public Ingredient(String name, Double weight) {
        this.name = name;
        this.weight = weight;

    }

    public String getName() {
        return name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }


    public String toString() {
        return name + "!" + weight;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}