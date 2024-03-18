//package com.example.recipeapp;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.ui.Model;
//import java.util.Arrays;
//import java.util.List;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class MenuController {
//    @GetMapping
//    public String menu(Model model) {
//        // Define sample product list
//        List<String> products = Arrays.asList("Apple", "Banana", "Carrot");
//
//        // Define sample recipe list
//        List<String> recipes = Arrays.asList("Fruit Salad", "Banana Bread", "Carrot Soup");
//
//        // Add sample lists to the model
//        model.addAttribute("products", products);
//        model.addAttribute("recipes", recipes);
//
//        return "menu"; // This will map to 'menu.html' in the 'templates' directory
//    }
//}
