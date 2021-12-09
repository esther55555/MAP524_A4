package com.example.recipe_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeActivity extends AppCompatActivity implements NetworkingService.NetworkingListener {
    NetworkingService networkingManager;
    JsonService jsonService;
    String recipeName;
    TextView recipeNameText;
    TextView cuisineTypeText;
    TextView mealTypeText;
    TextView totalTimeText;
    TextView ingredientsText;
    TextView caloriesText;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        //get access to the Views
        recipeNameText = findViewById(R.id.recipeName);
        cuisineTypeText = findViewById(R.id.cuisineType);
        mealTypeText = findViewById(R.id.mealType);
        totalTimeText = findViewById(R.id.totalTime);
        ingredientsText = findViewById(R.id.ingredients);
        caloriesText = findViewById(R.id.calories);
        imageView = findViewById(R.id.recipeImage);
        recipeNameText.setText(recipeName);

        //get the data sent from Main Activity
        //gets the name of the recipe selected
        recipeName = getIntent().getStringExtra("recipeName");

        networkingManager = ((myApp) getApplication()).getNetworkingService();
        jsonService = ((myApp) getApplication()).getJsonService();
        networkingManager.listener = this;

        //get the recipe info for the recipe selected
        networkingManager.getRecipeData(recipeName);
    }

    //listener for when recipe info is returned from background thread
    @Override
    public void dataListener(String jsonRecipeString) {
        Recipe data = jsonService.getRecipeData(recipeName, jsonRecipeString);

        cuisineTypeText.setText(data.getCuisineType());
        mealTypeText.setText(data.getMealType());
        totalTimeText.setText(data.getTime());
        ingredientsText.setText(data.getIngredients());
        caloriesText.setText(data.getCalories());

        //get the image using background thread
        networkingManager.getImageData(data.getImage());
    }

    //listener for when image is returned from background thread
    @Override
    public void imageListener(Bitmap image) {
        imageView.setImageBitmap(image);
    }
}