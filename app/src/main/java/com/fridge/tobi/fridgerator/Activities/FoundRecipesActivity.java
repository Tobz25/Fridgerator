package com.fridge.tobi.fridgerator.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fridge.tobi.fridgerator.Database.DBHelper;
import com.fridge.tobi.fridgerator.Model.Recipe;
import com.fridge.tobi.fridgerator.R;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * loads recipes from the database using the parameters and ingredients selected by the user and displays them in a listView
 */
public class FoundRecipesActivity extends AppCompatActivity {

    //DB helper object to perform database operations
    DBHelper myDbHelper;
    TextView tv [];
    // list which stores the loaded recipes
    public static List<Recipe> loaded;
    public final static String RECIPE_NAME = "com.fridge.tobi.fridgerator.recipeName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_found_recipes);

        Intent intent = getIntent();
        //list with ingredients typed in by the user in the SearchRecipeActivity
        List<String> ingredList = intent.getStringArrayListExtra(SearchRecipeActivity.INGRED_LIST);

        boolean vegetarian = intent.getBooleanExtra(SearchRecipeActivity.VEGETARIAN_VALUE, false);
        boolean vegan = intent.getBooleanExtra(SearchRecipeActivity.VEGAN_VALUE, false);

        //open connection to the database
        openDB();
        //load recipes from the database
        loaded = loadRecipes(ingredList, vegetarian, vegan);

        //adapter to fill the listview with the loaded recipes
        ListAdapter adapter = new ArrayAdapter<Recipe>(getApplicationContext(), R.layout.custom_textview, loaded);
        final ListView lv = (ListView) findViewById(R.id.RecipesListView);
        lv.setAdapter(adapter);

        //OnClickListener for the ListView Itesm. Starts the DetailsActivity to show the selected recipes' details.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), DetailsActivity.class);
                intent.putExtra(RECIPE_NAME, lv.getAdapter().getItem(position).toString());
                startActivity(intent);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    /**
     * Load recipes from the databse using the DBHelper class methods
     * @param ingredients
     * @param vegetarian
     * @param vegan
     * @return
     */
    public List<Recipe> loadRecipes(List<String> ingredients, boolean vegetarian, boolean vegan){
        List<Recipe> loadedRecipes = myDbHelper.loadRecipes(ingredients, vegetarian, vegan);
        return loadedRecipes;
    }

    /**
     * Open a connection to the gadgets internal database file
     */
    public void openDB(){

        try{
            myDbHelper = new DBHelper(this);
            myDbHelper.openDataBase();
        }
        catch(SQLException sqle){
            throw new Error ("Unable to open database");
        }
    }

}
