package com.example.recipe_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;

import java.util.ArrayList;
import java.util.List;

public class FavouritesActivity extends AppCompatActivity implements FavouritesAdapter.favouriteClickListener, DatabaseManager.DatabaseActionListener {
    DatabaseManager databaseManager = new DatabaseManager();
    RecyclerView recyclerView;
    FavouritesAdapter adapter;
    ArrayList<Recipe> favouriteRecipes = new ArrayList<>(0);
    AlertDialog.Builder builder;
    NetworkingService networkingManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_recipes);

        networkingManager = ((myApp) getApplication()).getNetworkingService();
        databaseManager.listener = this;

        recyclerView = findViewById(R.id.favouritesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FavouritesAdapter(this, favouriteRecipes);
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        databaseManager.getAllRecipesSaved();
    }

    public void getFavouriteRecipes() {
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
        builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setTitle(selectedRecipe.getName());
        builder.setMessage(Html.fromHtml("<p><b>" + "Cuisine Type: " + "</b><br>" + selectedRecipe.getCuisineType() + "</p><p><b>" + "Meal Type: " + "</b><br>" + selectedRecipe.mealType + "</p><p><b>" + "Calories: " + "</b><br>" + selectedRecipe.getCalories() + "</p><p><b>" + "Total Time: " + "</b><br>" + selectedRecipe.getTime() + "</p><p><b>" + "Ingredients: " + "</b><br>" + selectedRecipe.getIngredients() + "</p><br>"));
        builder.setNegativeButton("Full Link", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent toRecipeActivity = new Intent(getApplicationContext(), RecipeActivity.class);
                toRecipeActivity.putExtra("recipeName", selectedRecipe.getName());
                startActivity(toRecipeActivity);
            }
        });
        builder.show();
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            databaseManager.deleteRecipeFromDatabase(favouriteRecipes.get(viewHolder.getAdapterPosition()));
            databaseManager.getAllRecipesSaved();
        }
    };
}