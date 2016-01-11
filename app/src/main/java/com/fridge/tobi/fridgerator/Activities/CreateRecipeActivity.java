package com.fridge.tobi.fridgerator.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.fridge.tobi.fridgerator.Fragments.IngredientsAddedFragment;
import com.fridge.tobi.fridgerator.Fragments.IngredientsListAddFragment;
import com.fridge.tobi.fridgerator.Fragments.SearchFragment;
import com.fridge.tobi.fridgerator.R;

import java.util.ArrayList;
import java.util.List;

public class CreateRecipeActivity extends AppCompatActivity {

    private List<String> ingredientsList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadSpinner();
        IngredientsListAddFragment ingredientsAddFragment = new IngredientsListAddFragment();
        ingredientsAddFragment.setArguments(getIntent().getExtras());
        /**
         * add fragment which includes the EditText and Button to add recipes to the activity
         */
        getSupportFragmentManager().beginTransaction().add(R.id.addIngredients_container, ingredientsAddFragment).commit();

    }

    /**
     * Spinner is Dropdown menu representing the possible types of the recipe (starter, main, etc)
     */
    private void loadSpinner(){
        Spinner spinner = (Spinner) findViewById(R.id.recipeTypeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.recipeType_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SpinnerActivity());
    }

    public void IngredientsListAdd(View view){

        IngredientsAddedFragment ingredient = new IngredientsAddedFragment();
        EditText ingredName = (EditText) findViewById(R.id.recipeName);
        String message = ingredName.getText().toString();
        if(message.isEmpty()|| message == null)
            return;
        else {

                ingredientsList.add(message);
                Bundle args = new Bundle();
                args.putString("index", message);
                ingredient.setArguments(args);

                getSupportFragmentManager().beginTransaction().add(R.id.ingredientsList, ingredient).commit();
                ingredName.setText("");
        }
    }


}
