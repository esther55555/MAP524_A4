package com.example.recipe_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.TasksViewHolder> {
    interface favouriteClickListener {
        void favouriteRecipeClicked(Recipe selectedRecipe);
    }

    private Context mCtx;
    public List<Recipe> favouritesList;
    favouriteClickListener listener;

    public FavouritesAdapter(Context mCtx, List<Recipe> recipeList) {
        this.mCtx = mCtx;
        this.favouritesList = recipeList;
        listener = (favouriteClickListener) mCtx;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_favourites, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        Recipe t = favouritesList.get(position);
        holder.recipeTextView.setText(t.getName());
    }

    @Override
    public int getItemCount() {
        return favouritesList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView recipeTextView;

        public TasksViewHolder(View itemView) {
            super(itemView);

            recipeTextView = itemView.findViewById(R.id.favouriteRecipe);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Recipe city = favouritesList.get(getAdapterPosition());
            listener.favouriteRecipeClicked(city);
        }
    }
}
