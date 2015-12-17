package com.fridge.tobi.fridgerator.Database;


import android.database.sqlite.SQLiteDatabase;

import com.fridge.tobi.fridgerator.Database.SavedDataContract.IngredientTable;
import com.fridge.tobi.fridgerator.Database.SavedDataContract.RecipeTable;
/**
 * Created by Tobi on 17.12.2015.
 */
public class DBHelper {

    private static final String DATABASE_NAME ="Fridgerator.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_INGREDIENT =
            "CREATE TABLE" + IngredientTable.TABLE_NAME + " (" +
            IngredientTable._ID + " INTEGER PRIMARY KEY," +
            IngredientTable.COLUMN_NAME_NAME + " TEXT" +
            IngredientTable.COLUMN_NAME_TYP + " TEXT" +
            IngredientTable.COLUMN_NAME_VEGETARIAN + " INTEGER" +
            IngredientTable.COLUMN_NAME_VEAGN + " INTEGER" +
            " )";

    private static String SQL_CREATE_TABLE_RECIPE =
            "CREATE TABLE" + RecipeTable.TABLE_NAME + " (" +
            RecipeTable._ID + " INTEGER PRIMARY KEY," +
            RecipeTable.COLUMN_NAME_NAME + " TEXT" +
            RecipeTable.COLUMN_NAME_RATING + " INTEGER" +
            RecipeTable.COLUMN_NAME_AUTHORID + " TEXT" +
            RecipeTable.COLUMN_NAME_COMMENT + " TEXT" +
            RecipeTable.COLUMN_NAME_PROCEEDING + " TEXT" +
            " )";

    public void onCreate (SQLiteDatabase db){
        db.execSQL(SQL_CREATE_TABLE_INGREDIENT);
        db.execSQL(SQL_CREATE_TABLE_RECIPE);
    }
}
