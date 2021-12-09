package com.example.recipe_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecipesAdapter.recipeClickListener, NetworkingService.NetworkingListener{
    NetworkingService networkingManager;
    ArrayList<Recipe> recipes = new ArrayList<Recipe>(0);
    RecipesAdapter adapter;
    JsonService jsonService;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        networkingManager = ((myApp)getApplication()).getNetworkingService();
        networkingManager.listener = this;
        jsonService = ((myApp) getApplication()).getJsonService();
        recyclerView = findViewById(R.id.recipesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter = new RecipesAdapter(this, recipes);
        recyclerView.setAdapter(adapter);
        setTitle("Search for new recipes..");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchViewMenuItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) searchViewMenuItem.getActionView();
        String searchFor = searchView.getQuery().toString();
        if (!searchFor.isEmpty()) {
            searchView.setIconified(false);
            searchView.setQuery(searchFor, false);
        }

        searchView.setQueryHint("Search for recipes");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {// when the user clicks enter
                Log.d("query", query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {// after each char entered by user
                if (newText.length() >= 5) {
                    // search for recipes
                    networkingManager.searchForRecipes(newText);
                }
                else {
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
    public void dataListener(String jsonRecipeString) {
        recipes = jsonService.getRecipesFromJSON(jsonRecipeString);
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