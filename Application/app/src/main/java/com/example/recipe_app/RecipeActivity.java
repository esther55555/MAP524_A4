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

        recipeName = getIntent().getStringExtra("recipeName");

        networkingManager = ((myApp) getApplication()).getNetworkingService();
        jsonService = ((myApp) getApplication()).getJsonService();
        networkingManager.listener = this;

        networkingManager.getRecipeData(recipeName);

        recipeNameText = findViewById(R.id.recipeName);
        cuisineTypeText = findViewById(R.id.cuisineType);
        mealTypeText = findViewById(R.id.mealType);
        totalTimeText = findViewById(R.id.totalTime);
        ingredientsText = findViewById(R.id.ingredients);
        caloriesText = findViewById(R.id.calories);
        imageView = findViewById(R.id.recipeImage);
        recipeNameText.setText(recipeName);
    }

    @Override
    public void dataListener(String jsonRecipeString) {
        Recipe data = jsonService.getRecipeData(recipeName, jsonRecipeString);

        StringBuilder info = new StringBuilder();
        for (int i = 0; i < data.getCuisineType().size(); i++) {
            info.append(data.getCuisineType().get(i));

            if (data.getCuisineType().size() != i + 1) {
                info.append(", ");
            }
        }
        cuisineTypeText.setText(info.toString());

        info = new StringBuilder();
        for (int i = 0; i < data.getMealType().size(); i++) {
            info.append(data.getMealType().get(i));

            if (data.getMealType().size() != i + 1) {
                info.append(", ");
            }
        }
        mealTypeText.setText(info.toString());

        totalTimeText.setText(data.getTime());

        info = new StringBuilder();
        String readLine = "";
        for (int i = 0; i < data.getIngredients().size(); i++) {
            readLine += data.getIngredients().get(i);

            if (data.getIngredients().size() != i + 1) {
                info.append("- ").append(readLine).append("\n");
            }
        }
        ingredientsText.setText(info.toString());

        caloriesText.setText(data.getCalories());

        networkingManager.getImageData(data.getImage());
    }

    @Override
    public void imageListener(Bitmap image) {
        imageView.setImageBitmap(image);
    }
}