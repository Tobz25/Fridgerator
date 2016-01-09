package com.fridge.tobi.fridgerator.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.fridge.tobi.fridgerator.Database.SavedDataContract.RecipeTable;

import com.fridge.tobi.fridgerator.Model.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tobi on 03.01.2016.
 */
public class DBTasks {

    private Context context;
    DBHelper mDbHelper;

    public DBTasks(Context ctx){
        context = ctx;
    }

    public void insertRecipe(){
        mDbHelper = new DBHelper(context);
    }

    public Recipe loadRecipe(long id){
        mDbHelper = new DBHelper(context);
        List<Recipe> loadedRecipes = new ArrayList<Recipe>();

        Cursor cursor = mDbHelper.loadRecipe(id);
        cursor.moveToFirst();
        long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(RecipeTable._ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(RecipeTable.COLUMN_NAME_NAME));
        int rating = cursor.getInt(cursor.getColumnIndexOrThrow(RecipeTable.COLUMN_NAME_RATING));
        int authorID = cursor.getInt(cursor.getColumnIndexOrThrow(RecipeTable.COLUMN_NAME_AUTHORID));
        String proceeding = cursor.getString(cursor.getColumnIndexOrThrow(RecipeTable.COLUMN_NAME_PROCEEDING));

        Recipe rec = new Recipe(name,rating, authorID, proceeding);
        loadedRecipes.add(rec);
        return rec;
    }


}
