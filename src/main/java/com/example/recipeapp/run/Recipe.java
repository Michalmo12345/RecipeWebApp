package com.example.recipeapp.run;

import java.util.List;

public record Recipe (
    Integer id,
    String name,
    String description,
    Integer total_calories
    ) {}
