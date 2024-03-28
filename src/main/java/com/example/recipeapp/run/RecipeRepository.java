package com.example.recipeapp.run;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.util.Assert;

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

    public class RecipieRowMapper implements RowMapper<Recipe>{
        @Override
        public Recipe mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
            Recipe recipe = new Recipe();
            recipe.setId(rs.getInt("id"));
            recipe.setName(rs.getString("name"));
            recipe.setImage(rs.getString("image"));
            recipe.setCategoryText(rs.getString("category"));
            recipe.setInstructions(rs.getString("instructions"));
            recipe.setTime(rs.getDouble("time"));
            List<Ingredient> ingredients = new ArrayList<>();
            do {
                Ingredient ingredient = new Ingredient();
                ingredient.setName(rs.getString("name"));
                ingredient.setAmount(rs.getString("amount"));
                ingredient.setRecipe_id(rs.getInt("recipe_id"));
                ingredients.add(ingredient);
            } while (rs.next());
            recipe.setIngredients(ingredients);
            return recipe;
        }
    }

    public List<Recipe> findAll() {
        return jdbcClient.sql("select Recipe.*, Ingredient.name, Ingredient.amount, Ingredient.recipe_id from Recipe INNER join Ingredient on Recipe.id = Ingredient.recipe_id")
                .query(new RecipieRowMapper())
                .list();
    }

    public Optional<Recipe> findById(Integer id) {
        return jdbcClient.sql("select * from Recipe where id = :id")
                .param(id)
                .query(Recipe.class)
                .optional();
    }

    public void createIngredient(Ingredient ingredient) {
        jdbcClient.sql("INSERT INTO Ingredient(name, amount, recipe_id) values (?,?,?,?)")
                .params(List.of(ingredient.getName(), ingredient.getAmount(), ingredient.getRecipe_id()))
                .update();
    }
    public void create(Recipe recipe) {
        jdbcClient.sql("INSERT INTO Recipe(id, name, image, category, instructions, time) values (?, ?, ?, ?, ?, ?)")
                .params(List.of(recipe.getId(), recipe.getName(), recipe.getImage(), recipe.getCategoryText(), recipe.getInstructions(), recipe.getTime()))
                .update();
        for (Ingredient ingredient : recipe.getIngredients()) {
            this.createIngredient(ingredient);
        }
    }

    public void update(Recipe recipe, Integer id) {
        jdbcClient.sql("update Recipe set name = ?, image = ?, category = ?, instructions = ?, time = ? where id = ?")
                .params(List.of(recipe.getName(), recipe.getImage(), recipe.getCategoryText(), recipe.getInstructions(), recipe.getTime(), id))
                .update();
    }

    public void delete(Integer id) {
        jdbcClient.sql("delete from recipe where id = :id")
                .param("id", id)
                .update();
    }

    public void drop() {
        jdbcClient.sql("drop table recipe").update();
        jdbcClient.sql("drop table ingredient").update();
    }

    public int count() {
        return jdbcClient.sql("select count(*) from recipe").query().listOfRows().size();
    }

    public void saveAll(List<Recipe> recipes) {
        recipes.forEach(this::create);
    }

}
