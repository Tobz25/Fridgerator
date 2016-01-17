package com.fridge.tobi.fridgerator.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.fridge.tobi.fridgerator.R;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.fridge.tobi.fridgerator.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startSearch (View v) {
        Intent intent = new Intent();
        intent.setClass(this, SearchRecipeActivity.class);
        startActivity(intent);
    }

    public void startCreate (View v) {
        Intent intent = new Intent();
        intent.setClass(this, CreateRecipeActivity.class);
        startActivity(intent);
    }

    public void startImpressum (View v) {
        Intent intent = new Intent();
        intent.setClass(this, ImpressumActivity.class);
        startActivity(intent);
    }

    public void startEinkaufsliste (View v) {
        Intent intent = new Intent();
        intent.setClass(this, EinkaufslisteActivity.class);
        startActivity(intent);
    }


}
