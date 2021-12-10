package com.example.recipe_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class FavouritesActivity extends AppCompatActivity implements FavouritesAdapter.favouriteClickListener, DatabaseManager.DatabaseActionListener {
    DatabaseManager databaseManager = new DatabaseManager();
    RecyclerView recyclerView;
    FavouritesAdapter adapter;
    ArrayList<Recipe> favouriteRecipes = new ArrayList<>(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_recipes);

        databaseManager.listener = this;

        recyclerView = findViewById(R.id.favouritesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FavouritesAdapter(this, favouriteRecipes);
        recyclerView.setAdapter(adapter);

        databaseManager.getAllRecipesSaved();
    }

    public void getFavouriteRecipes(){
        databaseManager.getAllRecipesSaved();
    }

    @Override
    public void faveListener(List<Recipe> savedRecipes) {
        favouriteRecipes = new ArrayList<Recipe>(savedRecipes);
        adapter = new FavouritesAdapter(this, favouriteRecipes);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void favouriteRecipeClicked(Recipe selectedRecipe) {

    }
}