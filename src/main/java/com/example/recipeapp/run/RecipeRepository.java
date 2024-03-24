package com.example.recipeapp.run;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RecipeRepository {

    private static final Logger log = LoggerFactory.getLogger(RecipeRepository.class);
    private final JdbcClient jdbcClient;

    public RecipeRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Recipe> findAll() {
        return jdbcClient.sql("select * from Recipe")
                .query(Recipe.class)
                .list();
    }

    public Optional<Recipe> findById(Integer id) {
        return jdbcClient.sql("select id,name from Recipe where id = :id")
                .param(id)
                .query(Recipe.class)
                .optional();
    }

    public void create(Recipe recipe) {
        jdbcClient.sql("INSERT INTO Recipe(id, name) values (?, ?)")
                .params(List.of(recipe.getId(), recipe.getName()))
                .update();
    }

    public void update(Recipe recipe) {
        jdbcClient.sql("update recipes set name = :name, description = :description where id = :id")
                .params(List.of(recipe.getId(), recipe.getName()))
                .update();
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("delete from Recipe where id = :id")
                .param(id)
                .update();
    }

    public int count() { return jdbcClient.sql("select count(*) from recipe").query().listOfRows().size(); }

    public void saveAll(List<Recipe> recipes) {
        recipes.stream().forEach(this::create);
    }

}
