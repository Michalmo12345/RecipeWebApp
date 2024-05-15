package com.example.recipeapp.api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ApiHandler {
    protected final JSONParser parser = new JSONParser();
    protected final JSONObject jsonObject;
    {
        try {
            jsonObject = (JSONObject) parser.parse(new FileReader("APIKey.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected String getUserId(String objectName) {
        return (String) jsonObject.get(String.format("%sApiID", objectName));
    }

    protected String getApiKey(String objectName) {
        return (String) jsonObject.get(String.format("%sApiKey", objectName));
    }
}
