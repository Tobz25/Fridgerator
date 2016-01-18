package com.fridge.tobi.fridgerator.Activities;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;;

import com.fridge.tobi.fridgerator.Database.DBHelper;
import com.fridge.tobi.fridgerator.Fragments.IngredientsAddedFragment;
import com.fridge.tobi.fridgerator.Fragments.SearchFragment;
import com.fridge.tobi.fridgerator.R;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchRecipeActivity extends AppCompatActivity {

    public final static String INGRED_LIST = "com.fridge.tobi.fridgerator.INGREDIENTS";
    public final static String VEGETARIAN_VALUE = "com.fridge.tobi.fridgerator.VEGETARIAN";
    public final static String VEGAN_VALUE = "com.fridge.tobi.fridgerator.VEGAN";
    private int ingredientCounter = 0;
    public static List<String> ingredientsList = new ArrayList<String>();


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


    /**
     * Start FoundRecipesActivity to display the found recipes
     * @param view
     */
    public void searchRecipes(View view) {

        Intent intent = new Intent(this, FoundRecipesActivity.class);
        intent.putStringArrayListExtra(INGRED_LIST, (ArrayList<String>) ingredientsList);

        CheckBox checkBoxVegi = (CheckBox) findViewById(R.id.checkBoxVegetarian);
        CheckBox checkBoxVegan = (CheckBox) findViewById(R.id.checkBoxVegan);
        boolean vegetarian = checkBoxVegi.isChecked() ? true : false;
        boolean vegan = checkBoxVegan.isChecked() ? true : false;
        intent.putExtra(VEGETARIAN_VALUE, vegetarian);
        intent.putExtra(VEGAN_VALUE,vegan);
        startActivity(intent);

    }

    public void addIngredient (View view){
        IngredientsAddedFragment ingredient = new IngredientsAddedFragment();
        EditText ingredName = (EditText) findViewById(R.id.zutat);
        String message = ingredName.getText().toString();
        if(message.isEmpty()|| message == null)
            return;
        else {

            if (ingredientCounter >= 5) {
                ingredName.setText("");
                showMessage();
                return;
            } else {
                ingredientsList.add(message);
                Bundle args = new Bundle();
                args.putString("index", message);
                ingredient.setArguments(args);

                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_added, ingredient).commit();
                ingredName.setText("");
                ingredientCounter++;
            }
        }
    }

    private void showMessage(){
        AlertDialog.Builder ingredAlert = new AlertDialog.Builder(this);
        ingredAlert.setMessage(getResources().getString(R.string.errorMessage));
        ingredAlert.setPositiveButton(getResources().getString(R.string.confirmDeleteButton), null);
        ingredAlert.create().show();

    }


}
