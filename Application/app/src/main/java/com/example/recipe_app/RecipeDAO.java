package com.example.recipe_app;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecipeDAO {
    @Insert
    void insertNewRecipe(Recipe recipe);

    @Delete
    void deleteRecipe(Recipe recipe);

    @Query("Select * from Recipe")
    List<Recipe> getAllSavedRecipes();
}
