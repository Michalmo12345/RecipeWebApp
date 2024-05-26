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
//        returns a string of macros in the format: "calories, proteins, fat, carbs, fiber"
        String urlString = String.format(baseUrl, userId, apiKey, query).replace(" ", "%20");
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
                return "No data for that ingredient";
            }

            JSONObject ingredient = (JSONObject) parsed.get(0);
            JSONObject food = (JSONObject) ingredient.get("food");
            StringBuilder macros = new StringBuilder();
            if (food.containsKey("nutrients")) {
                JSONObject nutrients = (JSONObject) food.get("nutrients");
                double calories = (double) nutrients.get("ENERC_KCAL") * weight / 100;
                double proteins = (double) nutrients.get("PROCNT") * weight / 100;
                double fat = (double) nutrients.get("FAT") * weight / 100;
                double carbs = (double) nutrients.get("CHOCDF") * weight / 100;
                double fiber = (double) nutrients.get("FIBTG") * weight / 100;

                calories = Math.round(calories * 100.0) / 100.0;
                proteins = Math.round(proteins * 100.0) / 100.0;
                fat = Math.round(fat * 100.0) / 100.0;
                carbs = Math.round(carbs * 100.0) / 100.0;
                fiber = Math.round(fiber * 100.0) / 100.0;

                macros.append("Calories: ").append(calories).append(" kcal, ");
                macros.append("Proteins: ").append(proteins).append(" g, ");
                macros.append("Fat: ").append(fat).append(" g, ");
                macros.append("Carbs: ").append(carbs).append(" g, ");
                macros.append("Fiber: ").append(fiber).append(" g");
            } else {
                return "No data for that ingredient";
            }

            return macros.toString();
        } catch (ProtocolException e) {
            return "No data for that ingredient";
        } catch (MalformedURLException e) {
            return "No data for that ingredient";
        } catch (IOException e) {
            return "No data for that ingredient";
        } catch (ParseException e) {
            return "No data for that ingredient";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
