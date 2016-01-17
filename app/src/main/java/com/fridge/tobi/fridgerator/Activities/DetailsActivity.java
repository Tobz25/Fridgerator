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

public class DetailsActivity extends AppCompatActivity {
    String recipeName;
    String ingredient1;
    String ingredient2;
    String proceding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_details);

        Intent intent = getIntent();
        ((TextView)(findViewById(R.id.RecipeDetailsName))).setText(intent.getStringExtra(FoundRecipesActivity.RECIPE_NAME));
        recipeName = (intent.getStringExtra(FoundRecipesActivity.RECIPE_NAME));

        for(Recipe rec : FoundRecipesActivity.loaded) {
            if (rec.getName().equals(recipeName)) {
                ingredient1 = rec.getIngred1();
                ingredient2 = rec.getIngred2();
                proceding = rec.getProceeding();
            }
        }
        ((TextView)(findViewById(R.id.RecipeDetailsName))).setText(recipeName);
        ((TextView)(findViewById(R.id.ingred1))).setText(ingredient1);
        ((TextView)(findViewById(R.id.ingred2))).setText(ingredient2);
        ((TextView)(findViewById(R.id.proceeding))).setText(proceding);


/**
        LinearLayout layout = (LinearLayout) View.inflate(this, R.layout.content_details, null);


        //LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View v = vi.inflate(R.layout.content_details, null);
        //TextView test = (TextView)v.findViewById(R.id.RecipeDetailsName);
        //test.setText("Ueberschrift");


        Intent intent = getIntent();
        String recipeName = intent.getStringExtra(FoundRecipesActivity.RECIPE_NAME);
        getRecipeInformation(recipeName, layout);


        setContentView(layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

**/
    }

    public void getRecipeInformation(String name,LinearLayout layout){

        for(Recipe rec : FoundRecipesActivity.loaded){
            if(rec.getName().equals(name)){
                TextView tv;
               // TextView recipeName = (TextView)findViewById(R.id.RecipeDetailsName);
              //  TextView recipeName = new TextView(this);
              //  recipeName.setText(rec.getName());
                    tv = new TextView(this);
                    tv.setTextSize(40);
                    tv.setGravity(15);
                    tv.setPadding(0, 0, 0, 20);
                    tv.setText(rec.getName());
                    layout.addView(tv);

               // TextView ingredience1 = (TextView)findViewById(R.id.ingred1);
              //  ingredience1.setText(rec.getIngred1());
                tv = new TextView(this);
                tv.setTextSize(20);
                tv.setText(rec.getIngred1());
                layout.addView(tv);


               // TextView ingredience2 = (TextView)findViewById(R.id.ingred2);
                //ingredience2.setText(rec.getIngred2());
                tv = new TextView(this);
                tv.setTextSize(20);
                tv.setText(rec.getIngred2());
                layout.addView(tv);

                tv = new TextView(this);
                tv.setTextSize(20);
                tv.setText(rec.getProceeding());
                tv.setPadding(0, 20, 0, 0);
                layout.addView(tv);
            }

        }
    }

}
