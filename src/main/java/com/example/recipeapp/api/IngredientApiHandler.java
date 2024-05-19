package com.example.recipeapp.api;

import com.example.recipeapp.models.Ingredient;
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
    public String getIngredientMacros(String query, Double weight) {
//        quantity in grams
//        returns a string of macros in the format: "protein,calories,fat,carbs,fiber"
        String urlString = String.format(baseUrl, userId, apiKey, query);
        for (char c : query.toCharArray()) {
            if (c == ' ') {
                urlString = urlString.replace(" ", "%20");
            }
        }
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
            JSONArray parsed = (JSONArray) responseObj.get("parsed");
            if (parsed.isEmpty()) {
                throw new RuntimeException("No ingredients found");
            }
            JSONObject ingredient = (JSONObject) parsed.get(0);
            JSONObject food = (JSONObject) ingredient.get("food");
            String macros = "";
            if (food.containsKey("nutrients")) {
                JSONObject nutrients = (JSONObject) food.get("nutrients");
                for (Object key : nutrients.keySet()) {
                    macros += (Double) nutrients.get(key) * weight / 100 + ",";
                }
            }
            else {
                throw new RuntimeException("No nutrients found");
            }
            return macros;
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
//    public static void main(String[] args) {
//        IngredientApiHandler ingredientApiHandler = new IngredientApiHandler();
//        System.out.println(ingredientApiHandler.getIngredientMacros("butter", 100.0));
//    }
}
