package com.fridge.tobi.fridgerator.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fridge.tobi.fridgerator.Database.DBHelper;
import com.fridge.tobi.fridgerator.Model.Recipe;
import com.fridge.tobi.fridgerator.R;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoundRecipesActivity extends AppCompatActivity {

    DBHelper myDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = (LinearLayout) View.inflate(this, R.layout.content_found_recipes, null);
        //LinearLayout wrapper = (LinearLayout) findViewById(R.id.found_recipes_container);
        Intent intent = getIntent();
        ArrayList<String> ingredList = intent.getStringArrayListExtra(SearchRecipeActivity.INGRED_LIST);
        for(String ingred : ingredList){
            TextView ingredName = new TextView(this);
            ingredName.setTextSize(20);
            ingredName.setText(ingred);
            layout.addView(ingredName);
        }
        setContentView(layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //fillDatabase();
        loadRecipe();
    }

    public Recipe loadRecipe(){
        Recipe loadedRecipe = myDbHelper.loadRecipe(1);
        return loadedRecipe;
    }

    /**
     * Create the internal database and fill it with data from the file in the assets folder
     */
    /**
    public void fillDatabase(){

        myDbHelper = new DBHelper(this);

        try{
            myDbHelper.createDataBase();
        }
        catch (IOException ioe) {
            throw new Error ("Unable to create database");
        }

        try{
            myDbHelper.openDataBase();
        }
        catch(SQLException sqle){
            throw new Error ("Unable to open database");
        }



    }**/

}
