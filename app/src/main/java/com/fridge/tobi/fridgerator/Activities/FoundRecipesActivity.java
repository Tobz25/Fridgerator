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


public class FoundRecipesActivity extends AppCompatActivity {

    DBHelper myDbHelper;
    TextView tv [];
    public static List<Recipe> loaded;
    public final static String RECIPE_NAME = "com.fridge.tobi.fridgerator.recipeName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_found_recipes);

        //LinearLayout layout = (LinearLayout) View.inflate(this, R.layout.content_found_recipes, null);
        //LinearLayout wrapper = (LinearLayout) findViewById(R.id.found_recipes_container);
        Intent intent = getIntent();
        List<String> ingredList = intent.getStringArrayListExtra(SearchRecipeActivity.INGRED_LIST);

        boolean vegetarian = intent.getBooleanExtra(SearchRecipeActivity.VEGETARIAN_VALUE, false);
        boolean vegan = intent.getBooleanExtra(SearchRecipeActivity.VEGAN_VALUE, false);

        openDB();
        loaded = loadRecipes(ingredList, vegetarian, vegan);


        ListAdapter adapter = new ArrayAdapter<Recipe>(getApplicationContext(), R.layout.custom_textview, loaded);
        final ListView lv = (ListView) findViewById(R.id.RecipesListView);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), DetailsActivity.class);
                intent.putExtra(RECIPE_NAME, lv.getAdapter().getItem(position).toString());
                startActivity(intent);
            }
        });




        /**
        tv = new TextView[loaded.size()];
        View.OnClickListener onRecipeClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetails(v, loaded);
            }
        };


        for(int i =0; i< loaded.size(); ++i){
            tv[i] = new TextView(this);
            tv[i].setTextSize(40);
            tv[i].setText(loaded.get(i).getName());
            tv[i].setGravity(0);
            int id = (int)(loaded.get(i).getId());
            tv[i].setId(id);
            tv[i].setOnClickListener(onRecipeClick);

                    layout.addView(tv[i]);
        }**/

      //  setContentView(layout);
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

    /**
     * Show the details of a recipe in a new activity (DetailsActivity)
     * @param rec
     */
    public void showDetails(View v, List<Recipe> recipes){
        for(int i=0; i< tv.length;++i){
            if(tv[i] == v){
                Intent intent = new Intent(this, DetailsActivity.class);
                intent.putExtra(RECIPE_NAME, tv[i].getText());
              //  intent.(RECIPE_LIST, (ArrayList<Recipe>)recipes);
                startActivity(intent);
            }
        }

    }


}
