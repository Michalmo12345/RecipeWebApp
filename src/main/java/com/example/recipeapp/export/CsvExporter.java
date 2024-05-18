package com.example.recipeapp.export;

import com.example.recipeapp.models.Recipe;

import java.util.List;

public class CsvExporter {
    public static String convertToCSV(List<Recipe> recipes) {
        StringBuilder sb = new StringBuilder("ID,Title,Ingredients,Instructions\n");
        for (Recipe recipe : recipes) {
            sb.append(recipe.getName().replace(",", ";")).append(",");
            // sb.append(recipe.getIngredients().replace(",", ";")).append(",");
            sb.append(recipe.getCategory().replace(",", ";")).append(",");
            sb.append(recipe.getInstructions().replace(",", ";")).append("\n");
        }
        return sb.toString();
    }
}
