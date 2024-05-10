package com.example.recipeapp.api;

import com.example.recipeapp.models.Ingredient;
import com.example.recipeapp.models.Recipe;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class RecipeApiHandler extends ApiHandler{
    private final String userId = getUserId("recipe");
    private final String apiKey = getApiKey("recipe");
    private final String baseUrl = "https://api.edamam.com/api/recipes/v2?type=public&q=%s&app_id=%s&app_key=%s&field=label&field=image&field=url&field=ingredientLines&field=ingredients&field=calories&field=totalTime&field=cuisineType&field=totalNutrients";

    public List<Recipe> getRecipes(String query) {
        String urlString = String.format(baseUrl, query, userId, apiKey);
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();
            JSONObject responseObj = (JSONObject) parser.parse(response.toString());
            JSONArray hits = (JSONArray) responseObj.get("hits");
            if (hits.isEmpty()) {
                throw new RuntimeException("No recipes found");
            }
            List<Recipe> recipes = new ArrayList<>();
            for (int i = 0; i < hits.size(); i++) {
                JSONObject recipeObj = (JSONObject) hits.get(i);
                JSONObject recipe = (JSONObject) recipeObj.get("recipe");
                String name = (String) recipe.get("label");
                String image = (String) recipe.get("image");
                String category = ((ArrayList<?>) recipe.get("cuisineType")).get(0).toString();
                String instructions = (String) recipe.get("url");
                Double time_d = (Double) recipe.get("totalTime");
                int time = time_d.intValue();
//                TODO: Make proper cast to List<Ingredient>
//                JSONArray ingredients = (JSONArray) recipe.get("ingredients");
//                List<Ingredient> ingredientList = new ObjectMapper().readValue(ingredients.toJSONString(), List.class);
                List<Ingredient> ingredients = new ArrayList<>();
                recipes.add(new Recipe(i, image, name, category, instructions, time, ingredients));
            }
            return recipes;

    } catch (ProtocolException e) {
            throw new RuntimeException("Invalid request method");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL");
        } catch (IOException e) {
            throw new RuntimeException("Error connecting to API");
        } catch (ParseException e) {
            throw new RuntimeException("Error parsing response");
        }
    }
}