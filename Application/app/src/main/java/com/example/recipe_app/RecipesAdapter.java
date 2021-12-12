package com.example.recipe_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.TasksViewHolder> {
    interface recipeClickListener {
        void recipeClicked(Recipe selectedRecipe);
    }

    private Context mCtx;
    public List<Recipe> recipeList;
    recipeClickListener listener;

    public RecipesAdapter(Context mCtx, List<Recipe> recipeList) {
        this.mCtx = mCtx;
        this.recipeList = recipeList;
        listener = (recipeClickListener) mCtx;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_recipes, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        Recipe t = recipeList.get(position);
        holder.recipeTextView.setText(t.getName());
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView recipeTextView;

        public TasksViewHolder(View itemView) {
            super(itemView);

            recipeTextView = itemView.findViewById(R.id.recipe);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Recipe recipe = recipeList.get(getAdapterPosition());
            listener.recipeClicked(recipe);
        }
    }
}