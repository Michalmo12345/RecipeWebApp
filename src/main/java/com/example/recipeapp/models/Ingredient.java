package com.example.recipeapp.models;

import jakarta.persistence.*;

import java.util.Map;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    private String name;
    private String amount;


    @ManyToOne
    @JoinColumn(name = "recipe_id",nullable = false)
    private Recipe recipe;

}
