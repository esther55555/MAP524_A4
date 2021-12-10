package com.example.recipe_app;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String ingredients;
    public String calories;
    public String time;
    public String cuisineType;
    public String mealType;

    @Ignore
    public String image;

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

    public void setNewRecipe(String name, String ingredients, String calories, String time, String cuisineType, String mealType){
        this.name = name;
        this.ingredients = ingredients;
        this.calories = calories;
        this.time = time;
        this.cuisineType = cuisineType;
        this.mealType = mealType;
    }
}