package com.example.recipe_app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonService {
    public ArrayList<Recipe> getRecipesFromJSON(String jsonRecipeString) {
        ArrayList<Recipe> recipeList = new ArrayList<>(0);

        try {
            JSONObject jsonObject = new JSONObject(jsonRecipeString);

            JSONArray hitsArray = jsonObject.getJSONArray("hits");

            for (int i = 0; i < hitsArray.length(); i++) {
                JSONObject hitsDataObject = hitsArray.getJSONObject(i);
                JSONObject recipeDataObject = hitsDataObject.getJSONObject("recipe");
                String recipeName = recipeDataObject.getString("label");

                Recipe newRecipe = new Recipe(recipeName);
                recipeList.add(newRecipe);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    public Recipe getRecipeData(String name, String jsonRecipeString) {
        Recipe recipeData = new Recipe();

        try {
            JSONObject jsonObject = new JSONObject(jsonRecipeString);

            JSONArray hitsArray = jsonObject.getJSONArray("hits");

            for (int i = 0; i < hitsArray.length(); i++) {
                JSONObject hitsDataObject = hitsArray.getJSONObject(i);
                JSONObject recipeDataObject = hitsDataObject.getJSONObject("recipe");
                String recipeName = recipeDataObject.getString("label");
                String image = recipeDataObject.getString("image");

                if (recipeName.equals(name)) {
                    //INGREDIENTS FIELD
                    //converted into a String, so it can be saved into the database
                    JSONArray ingredientsLinesData = recipeDataObject.getJSONArray("ingredientLines");
                    String ingredientString = "";
                    for (int j = 0; j < ingredientsLinesData.length(); j++) {
                        ingredientString += ingredientsLinesData.getString(j);
                        if (ingredientsLinesData.length() != j + 1) {
                            ingredientString += ", ";
                        }
                    }

                    //CUISINE TYPE FIELD
                    //converted into a String, so it can be saved into the database
                    JSONArray cuisineTypeData = recipeDataObject.getJSONArray("cuisineType");
                    String cuisineString = "";
                    for (int j = 0; j < cuisineTypeData.length(); j++) {
                        cuisineString += cuisineTypeData.getString(j);
                        if (cuisineTypeData.length() != j + 1) {
                            cuisineString += ", ";
                        }
                    }

                    //MEAL TYPE FIELD
                    //converted into a String, so it can be saved into the database
                    JSONArray mealTypeData = recipeDataObject.getJSONArray("mealType");
                    String mealString = "";
                    for (int j = 0; j < mealTypeData.length(); j++) {
                        mealString += mealTypeData.getString(j);
                        if (mealTypeData.length() != j + 1) {
                            mealString += ", ";
                        }
                    }

                    //CALORIES FIELD
                    String caloriesData = recipeDataObject.getString("calories");

                    //TIME FIELD
                    String totalTimeData = recipeDataObject.getString("totalTime");

                    //CREATE THE RECIPE
                    recipeData = new Recipe(recipeName, ingredientString, caloriesData, totalTimeData, cuisineString, mealString, image);
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recipeData;
    }
}