package com.example.recipe_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecipesAdapter.recipeClickListener, NetworkingService.NetworkingListener {
    NetworkingService networkingManager;
    ArrayList<Recipe> recipes = new ArrayList<Recipe>(0);
    RecipesAdapter adapter;
    JsonService jsonService;
    RecyclerView recyclerView;
    DatabaseManager databaseManager = new DatabaseManager();
    TextView mainTitle;
    TextView mainDescription;
    MenuItem searchViewMenuItem;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseManager.getDatabaseInstance(this);
        networkingManager = ((myApp) getApplication()).getNetworkingService();
        networkingManager.listener = this;
        jsonService = ((myApp) getApplication()).getJsonService();
        recyclerView = findViewById(R.id.recipesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainTitle = findViewById(R.id.title);
        mainDescription = findViewById(R.id.description);

        adapter = new RecipesAdapter(this, recipes);
        recyclerView.setAdapter(adapter);
        setTitle(getString(R.string.main_search_for_new_recipes));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        inflater.inflate(R.menu.option_menu, menu);

        searchViewMenuItem = menu.findItem(R.id.search);

        searchView = (SearchView) searchViewMenuItem.getActionView();
        String searchFor = searchView.getQuery().toString();
        if (!searchFor.isEmpty()) {
            searchView.setIconified(false);
            searchView.setQuery(searchFor, false);
        }

        searchView.setQueryHint(getString(R.string.main_search_for_recipes));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {// when the user clicks enter
                networkingManager.searchForRecipes(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {// after each char entered by user
                if (newText.length() >= 1) {
                    // search for recipes
                    networkingManager.searchForRecipes(newText);
                } else {
                    recipes = new ArrayList<>(0);
                    adapter.recipeList = recipes;
                    adapter.notifyDataSetChanged();
                }
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        Intent toFavouriteRecipesActivity = new Intent(this, FavouritesActivity.class);
        startActivity(toFavouriteRecipesActivity);
        return true;
    }

    @Override
    public void dataListener(String jsonRecipeString) {
        recipes = jsonService.getRecipesFromJSON(jsonRecipeString);
        if (recipes.size() > 0) {
            mainTitle.setText("");
            mainDescription.setText("");
        } else {
            mainTitle.setText(R.string.main_title);
            mainDescription.setText(R.string.main_description);
        }
        adapter = new RecipesAdapter(this, recipes);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void imageListener(Bitmap image) {

    }

    @Override
    public void recipeClicked(Recipe selectedRecipe) {
        Intent intent = new Intent(this, RecipeActivity.class);
        intent.putExtra("recipeName", selectedRecipe.getName());
        startActivity(intent);
    }
}