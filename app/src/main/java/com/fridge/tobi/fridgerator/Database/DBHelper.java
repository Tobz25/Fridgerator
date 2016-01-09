package com.fridge.tobi.fridgerator.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fridge.tobi.fridgerator.Database.SavedDataContract.IngredientTable;
import com.fridge.tobi.fridgerator.Database.SavedDataContract.RecipeTable;
import com.fridge.tobi.fridgerator.Database.SavedDataContract.IngredientsInRecipeTable;
import com.fridge.tobi.fridgerator.Model.Recipe;

/**
 * Created by Tobi on 17.12.2015.
 */
public class DBHelper extends SQLiteOpenHelper {

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
            RecipeTable.COLUMN_NAME_AUTHORID + " INTEGER" +
           // RecipeTable.COLUMN_NAME_COMMENT + " TEXT" +
            RecipeTable.COLUMN_NAME_PROCEEDING + " TEXT" +
            " )";

    private static String SQL_CREATE_TABLE_IngredInRecipe=
            "CREATE TABLE" + IngredientsInRecipeTable.TABLE_NAME + " (" +
                    IngredientsInRecipeTable._ID + " INTEGER PRIMARY KEY," +
                    IngredientsInRecipeTable.COLUMN_NAME_INGREDIENTID + " INTEGER" +
                    IngredientsInRecipeTable.COLUMN_NAME_RECIPEID + " INTEGER" +
                    IngredientsInRecipeTable.COLUMN_NAME_QUANTITY + " TEXT" +
                    IngredientsInRecipeTable.COLUMN_NAME_UNIT + " TEXT" +
                    " )";

    private static final String SQL_DROP_TABLE_INGREDIENT = "DROP TABLE IF EXISTS " + IngredientTable.TABLE_NAME + ";";
    private static final String SQL_DROP_TABLE_RECIPE = "DROP TABLE IF EXISTS " + RecipeTable.TABLE_NAME + ";";
    private static final String SQL_DROP_TABLE_INGREDinRECIPE = "DROP TABLE IF EXISTS " + IngredientsInRecipeTable.TABLE_NAME + ";";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate (SQLiteDatabase db){
        db.execSQL(SQL_CREATE_TABLE_INGREDIENT);
        db.execSQL(SQL_CREATE_TABLE_RECIPE);
        db.execSQL(SQL_CREATE_TABLE_IngredInRecipe);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DROP_TABLE_INGREDIENT);
        db.execSQL(SQL_DROP_TABLE_RECIPE);
        db.execSQL(SQL_DROP_TABLE_INGREDinRECIPE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long insertRecipe(Recipe recipe){

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of recipe-values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(RecipeTable.COLUMN_NAME_NAME, recipe.getName());
        values.put(RecipeTable.COLUMN_NAME_RATING, recipe.getRating());
        values.put(RecipeTable.COLUMN_NAME_AUTHORID, recipe.getAuthorId());
        values.put(RecipeTable.COLUMN_NAME_PROCEEDING, recipe.getProceeding());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(RecipeTable.TABLE_NAME, null, values);
        return newRowId;
    }

    public Cursor loadRecipe(long id){
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                RecipeTable._ID,
                RecipeTable.COLUMN_NAME_NAME,
                RecipeTable.COLUMN_NAME_RATING,
                RecipeTable.COLUMN_NAME_AUTHORID,
                RecipeTable.COLUMN_NAME_PROCEEDING,
        };

        String selection = RecipeTable._ID;
        String []selectionArgs = {Long.toString(id)};

        Cursor c = db.query(
                RecipeTable.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // The sort order
        );
        //if(c!= null)
            return c;
    }
}
