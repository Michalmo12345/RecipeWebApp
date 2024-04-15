package com.example.recipeapp.api;

import com.example.recipeapp.run.Ingredient;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngredientApiHandler extends ApiHandler {
    private final String userId = getUserId("ingredient");
    private final String apiKey = getApiKey("ingredient");
    private final String baseUrl = "https://api.edamam.com/api/food-database/v2/parser?app_id=%s&app_key=%s&ingr=%s&nutrition-type=cooking";
    public List<Ingredient> getIngredients(String query) {
        String urlString = String.format(baseUrl, userId, apiKey, query);
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
            JSONArray hints = (JSONArray) responseObj.get("hints");
            if (hints.isEmpty()) {
                throw new RuntimeException("No ingredients found");
            }
            List<Ingredient> ingredients = new ArrayList<>();
            for (int i = 0; i < hints.size(); i++) {
                JSONObject ingredientObj = (JSONObject) hints.get(i);
                JSONObject ingredient = (JSONObject) ingredientObj.get("food");
                String name = (String) ingredient.get("label");
                JSONObject nutrients = (JSONObject) ingredient.get("nutrients");
                if (nutrients.isEmpty()) {
                    throw new RuntimeException("No nutrients found");
                }
                Map<String, Double> nutrientsProper = new HashMap<>();
                for (int j = 0; j < nutrients.size(); j++) {
                    nutrientsProper.put("KCAL", (Double) nutrients.get("ENERC_KCAL"));
                    nutrientsProper.put("Proteins", (Double) nutrients.get("PROCNT"));
                    nutrientsProper.put("Fats", (Double) nutrients.get("FAT"));
                    nutrientsProper.put("Carbs", (Double) nutrients.get("CHOCDF"));
                    nutrientsProper.put("Fiber", (Double) nutrients.get("FIBTG"));
                }
                JSONArray measures = (JSONArray) ingredientObj.get("measures");
                if (measures.isEmpty()) {
                    throw new RuntimeException("No measures found");
                }
                JSONObject measure = (JSONObject) measures.get(0);
                String amount = measure.get("weight").toString();
//                for (int j = 0; j < measures.size(); j++) {
//                    JSONObject measure = (JSONObject) measures.get(j);
//                    if (measure.get("label") == "Serving") {
//                        amount = measure.get("weight").toString();
//                    }
//                }
//                if (amount == null) {
//                    throw new RuntimeException("No amount found");
//                }
                ingredients.add(new Ingredient(name, amount, nutrientsProper));
            }
            return ingredients;

        }
        catch (ProtocolException e) {
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
