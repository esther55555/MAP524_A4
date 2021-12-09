package com.example.recipe_app;

import android.widget.ImageView;

import java.util.ArrayList;

public class Recipe {
    private String name;
    private ArrayList<String> ingredients;
    private String calories;
    private String time;
    private ArrayList<String> cuisineType;
    private ArrayList<String> mealType;
    private String image;

    public Recipe(){

    }

    public Recipe(String name){
        this.name = name;
    }

    public Recipe(String name, ArrayList<String> ingredients, String calories, String time, ArrayList<String> cuisineType, ArrayList<String> mealType, String image) {
        this.name = name;
        this.ingredients = ingredients;
        this.calories = calories;
        this.time = time;
        this.cuisineType = cuisineType;
        this.mealType = mealType;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public String getCalories() {
        return calories;
    }

    public String getTime() {
        return time;
    }

    public ArrayList<String> getCuisineType() {
        return cuisineType;
    }

    public ArrayList<String> getMealType() {
        return mealType;
    }
}