package com.fridge.tobi.fridgerator.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fridge.tobi.fridgerator.Model.Recipe;
import com.fridge.tobi.fridgerator.R;

/**
 * Shows the details of a recipe, which has been selected by the user
 */
public class DetailsActivity extends AppCompatActivity {
    String recipeName;
    String ingredient1;
    String ingredient2;
    String proceding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_details);

        // get the name of the selected recipe by the intent passed by the FoundRecipeActivity
        Intent intent = getIntent();
        ((TextView)(findViewById(R.id.RecipeDetailsName))).setText(intent.getStringExtra(FoundRecipesActivity.RECIPE_NAME));
        recipeName = (intent.getStringExtra(FoundRecipesActivity.RECIPE_NAME));

        //search the recipe in the List, which contains all loaed recipes, and get required information
        for(Recipe rec : FoundRecipesActivity.loaded) {
            if (rec.getName().equals(recipeName)) {
                ingredient1 = rec.getIngred1();
                ingredient2 = rec.getIngred2();
                proceding = rec.getProceeding();
            }
        }
        //display the recipe information
        ((TextView)(findViewById(R.id.RecipeDetailsName))).setText(recipeName);
        ((TextView)(findViewById(R.id.ingred1))).setText(ingredient1);
        ((TextView)(findViewById(R.id.ingred2))).setText(ingredient2);
        ((TextView)(findViewById(R.id.proceeding))).setText(proceding);

    }

}
