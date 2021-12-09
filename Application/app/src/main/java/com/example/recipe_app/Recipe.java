package com.example.recipe_app;

public class Recipe {
    private String name;
    private String ingredients;
    private String calories;
    private String time;
    private String cuisineType;
    private String mealType;
    private String image;

    public Recipe() {

    }

    public Recipe(String name) {
        this.name = name;
    }

    public Recipe(String name, String ingredients, String calories, String time, String cuisineType, String mealType, String image) {
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

    public String getIngredients() {
        return ingredients;
    }

    public String getCalories() {
        return calories;
    }

    public String getTime() {
        return time;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public String getMealType() {
        return mealType;
    }
}