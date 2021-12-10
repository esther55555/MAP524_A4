package com.example.recipe_app;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseManager {
    static RecipeDatabase database;

    public static final ExecutorService executorService = Executors.newFixedThreadPool(4);
    static Handler handler =new Handler(Looper.getMainLooper());

    interface DatabaseActionListener{
        void faveListener (List<Recipe> savedRecipes);
    }
    DatabaseActionListener listener;

    //build the database -- should only be called once
    public static void buildDatabaseInstance(Context context) {
        database = Room.databaseBuilder(context, RecipeDatabase.class, "recipe-database").build();
    }

    //gets the database instance
    public RecipeDatabase getDatabaseInstance(Context context) {
        // checks if the database has been built yet
        // ensures the database is only built ONCE
        if (database == null) {
            buildDatabaseInstance(context);
        }

        return database;
    }

    public void getAllRecipesSaved(){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Recipe> savedRecipes = database.getRecipeDAO().getAllSavedRecipes();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.faveListener(savedRecipes);
                    }
                });
            }
        });
    }

    public static void insertRecipeIntoDatabase(Recipe newRecipe){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                database.getRecipeDAO().insertNewRecipe(newRecipe);
            }
        });
    }
}
