package com.example.recipeapp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
public class RecipeController {


    private Map<String, Recipe> db = new HashMap<>()
    {{
        put("1", new Recipe("Chicken with rice", "1", 1200));
    }};

    @GetMapping("/recipes")
    public Collection<Recipe> get(){
        return db.values();
    }

    @GetMapping("/recipes/{id}")
    public Recipe get(@PathVariable String id)
    {
        Recipe recipe = db.get(id);
        if (recipe == null) throw new ResponseStatusException((HttpStatus.NOT_FOUND));
        return recipe;
    }

   @DeleteMapping("/recipes/{id}")
    public void delete(@PathVariable String id)
   {
       Recipe recipe = db.remove(id);
       if (recipe == null) throw new ResponseStatusException((HttpStatus.NOT_FOUND));
   }

   @PostMapping("/recipes")
    public Recipe create(@RequestBody Recipe recipe)
   {
       recipe.setId(UUID.randomUUID().toString());
       db.put(recipe.getId(),recipe);
       return recipe;
   }
}
