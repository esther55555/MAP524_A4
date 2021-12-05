package com.example.recipe_app;

import java.util.ArrayList;

public class Recipe {
    private String name = "";
    private ArrayList<String> healthLabel;
    private ArrayList<String> cautions;
    private ArrayList<String> ingredients;
    private String calories;
    private String time;
    private ArrayList<String> cuisineType;
    private ArrayList<String> mealType;

    public Recipe(){

    }

    public Recipe(String name){
        this.name = name;
    }

    public Recipe(String name, ArrayList<String> healthLabel, ArrayList<String> cautions, ArrayList<String> ingredients, String calories, String time, ArrayList<String> cuisineType, ArrayList<String> mealType) {
        this.name = name;
        this.healthLabel = healthLabel;
        this.cautions = cautions;
        this.ingredients = ingredients;
        this.calories = calories;
        this.time = time;
        this.cuisineType = cuisineType;
        this.mealType = mealType;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getHealthLabel() {
        return healthLabel;
    }

    public ArrayList<String> getCautions() {
        return cautions;
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