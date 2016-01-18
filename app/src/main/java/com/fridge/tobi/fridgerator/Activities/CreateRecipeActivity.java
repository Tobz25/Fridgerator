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

/**
 * Activity in which the user can create a new recipe
 */
public class CreateRecipeActivity extends AppCompatActivity {

    /**
     * Stores the ingredients for the recipe
     */
    public static List<String> createRecipeIngredientsList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * Containes the menu type (starter, main,..)
         */
        loadSpinner();
        IngredientsListAddFragment ingredientsAddFragment = new IngredientsListAddFragment();
        ingredientsAddFragment.setArguments(getIntent().getExtras());
        /**
         * add fragment which includes the EditText and Button to add recipes to the activity
         */
        getSupportFragmentManager().beginTransaction().add(R.id.addIngredients_container, ingredientsAddFragment).commit();

    }

    /**
     * Spinner is a Dropdown menu representing the possible types of the recipe (starter, main, etc)
     */
    private void loadSpinner(){
        Spinner spinner = (Spinner) findViewById(R.id.recipeTypeSpinner);

        //The adapter provides the choices for the spinner. They are an array loaded the strings.xml file
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.recipeType_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SpinnerActivity());
    }

    /**
     * Add ingredients for the recipe in a List and starts a fragment to display them
     * @param view
     */
    public void IngredientsListAdd(View view){
        //the fragment to display the ingredients; a new fragment is created for each ingredient
        IngredientsAddedFragment ingredient = new IngredientsAddedFragment();
        EditText ingredName = (EditText) findViewById(R.id.ingredientName);
        //the ingredient name, typed in by the user
        String message = ingredName.getText().toString();
        //do not add empty Strings
        if(message.isEmpty()|| message == null)
            return;
        else {
                //add the ingredient to the ingredients list
                createRecipeIngredientsList.add(message);
                Bundle args = new Bundle();
                args.putString("index", message);
                ingredient.setArguments(args);
                //add the fragment with the ingredient to a fragment container in the activity
                getSupportFragmentManager().beginTransaction().add(R.id.ingredientsListContainer, ingredient).commit();
                ingredName.setText("");
        }
    }


}
