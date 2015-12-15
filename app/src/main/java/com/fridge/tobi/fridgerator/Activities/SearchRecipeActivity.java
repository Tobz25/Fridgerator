package com.fridge.tobi.fridgerator.Activities;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;;

import com.fridge.tobi.fridgerator.Fragments.FoundRecipeFragment;
import com.fridge.tobi.fridgerator.Fragments.IngredientsAddedFragment;
import com.fridge.tobi.fridgerator.Fragments.SearchFragment;
import com.fridge.tobi.fridgerator.R;

public class SearchRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipe);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SearchFragment searchFragment = new SearchFragment();
        searchFragment.setArguments(getIntent().getExtras());

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, searchFragment).commit();
    }


    public void searchRecipes(View view) {

        FoundRecipeFragment foundRecipeFrag = new FoundRecipeFragment();
        Bundle args = new Bundle();
        foundRecipeFrag.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, foundRecipeFrag);
        transaction.addToBackStack(null);

        transaction.commit();

    }

    public void addIngredient (View view){
        IngredientsAddedFragment ingredient = new IngredientsAddedFragment();

        EditText ingredName = (EditText) findViewById(R.id.zutat);
        String message = ingredName.getText().toString();
        Bundle args = new Bundle();
        args.putString("index", message);
        ingredient.setArguments(args);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_added, ingredient).commit();

    }
}
