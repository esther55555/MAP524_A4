package com.example.recipe_app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonService {
    public ArrayList<Recipe> getRecipesFromJSON(String jsonRecipeString){
        ArrayList<Recipe> recipeList = new ArrayList<>(0);

        try{
            JSONObject jsonObject = new JSONObject(jsonRecipeString);

            JSONArray hitsArray = jsonObject.getJSONArray("hits");

            for (int i = 0; i < hitsArray.length(); i++){
                JSONObject hitsDataObject = hitsArray.getJSONObject(i);
                JSONObject recipeDataObject = hitsDataObject.getJSONObject("recipe");
                String recipeName = recipeDataObject.getString("label");

                Recipe newRecipe = new Recipe(recipeName);
                recipeList.add(newRecipe);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return  recipeList;
    }

    public Recipe getRecipeData(String name, String jsonRecipeString){
        Recipe recipeData = new Recipe();

        try{
            JSONObject jsonObject = new JSONObject(jsonRecipeString);

            JSONArray hitsArray = jsonObject.getJSONArray("hits");

            for (int i = 0; i < hitsArray.length(); i++){
                JSONObject hitsDataObject = hitsArray.getJSONObject(i);
                JSONObject recipeDataObject = hitsDataObject.getJSONObject("recipe");
                String recipeName = recipeDataObject.getString("label");

                if (recipeName.equals(name)){
                    JSONArray healthLabelsData = recipeDataObject.getJSONArray("healthLabels");
                    ArrayList<String> healthLabels = new ArrayList<>(0);
                    for (int j = 0; j < healthLabelsData.length(); j++){
                        healthLabels.add(healthLabelsData.getString(j));
                    }

                    JSONArray cautionsData = recipeDataObject.getJSONArray("cautions");
                    ArrayList<String> cautions = new ArrayList<>(0);
                    for (int j = 0; j < cautionsData.length(); j++){
                        cautions.add(cautionsData.getString(j));
                    }

                    JSONArray ingredientsLinesData = recipeDataObject.getJSONArray("ingredientLines");
                    ArrayList<String> ingredientLines = new ArrayList<>(0);
                    for (int j = 0; j < ingredientsLinesData.length(); j++){
                        ingredientLines.add(ingredientsLinesData.getString(j));
                    }

                    String caloriesData = recipeDataObject.getString("calories");
                    String totalTimeData = recipeDataObject.getString("totalTime");

                    JSONArray cuisineTypeData = recipeDataObject.getJSONArray("cuisineType");
                    ArrayList<String> cuisineType = new ArrayList<>(0);
                    for (int j = 0; j < cuisineTypeData.length(); j++){
                        cuisineType.add(cuisineTypeData.getString(j));
                    }

                    JSONArray mealTypeData = recipeDataObject.getJSONArray("mealType");
                    ArrayList<String> mealType = new ArrayList<>(0);
                    for (int j = 0; j < mealTypeData.length(); j++){
                        mealType.add(mealTypeData.getString(j));
                    }

                    recipeData = new Recipe(recipeName, healthLabels, cautions, ingredientLines, caloriesData, totalTimeData, cuisineType, mealType);
                    break;
                }
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        return recipeData;
    }
}
