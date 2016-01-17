package com.fridge.tobi.fridgerator.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.fridge.tobi.fridgerator.Database.SavedDataContract.IngredientTable;
import com.fridge.tobi.fridgerator.Database.SavedDataContract.RecipeTable;
import com.fridge.tobi.fridgerator.Database.SavedDataContract.IngredientsInRecipeTable;
import com.fridge.tobi.fridgerator.Model.Recipe;

import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import android.text.TextUtils;

/**
 * Created by Tobi on 17.12.2015.
 */

public class DBHelper extends SQLiteOpenHelper {

    /**
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
    }**/

    /**----------------------------------------------------------------------------------------**/

    private static String DB_PATH = "/data/data/com.fridge.tobi.fridgerator/databases/";

    private static String DB_NAME = "ingredients";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    public DBHelper (Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    public void createDataBase() throws IOException {


        File f = new File(DB_PATH);
        if(!f.exists()){
            f.mkdir();
        }
/**
        boolean dbExit = checkDataBase();

        if(dbExit) {
            //System.out.print("DB existiert");
            SQLiteDatabase.deleteDatabase(new File(DB_NAME));
        }**/
      //  else {
            //System.out.print("DB existiert noch nicht");
            this.getReadableDatabase();
            try {
                copyDataBase();
            }
            catch (IOException e) {
                throw new Error ("Error copying database");
            }
      //  }
    }


    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
        }
        if(checkDB != null){
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException{

        InputStream myInput = myContext.getAssets().open(DB_NAME);

        String outFileName = DB_PATH + DB_NAME;

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException{

        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath,null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close(){

        if(myDataBase != null)
            myDataBase.close();

        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
    public Cursor loadRecipeCursor(long id){

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                RecipeTable._ID,
                RecipeTable.COLUMN_NAME_NAME,
                RecipeTable.COLUMN_NAME_RATING,
                RecipeTable.COLUMN_NAME_AUTHORID,
                RecipeTable.COLUMN_NAME_PROCEEDING,
        };

        String selection = RecipeTable._ID + "=?";
        String []selectionArgs = {Long.toString(id)};

        try {
            Cursor c = myDataBase.query(
                    RecipeTable.TABLE_NAME,  // The table to query
                    projection,                               // The columns to return
                    selection,                                // The columns for the WHERE clause
                    selectionArgs,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null                                      // The sort order
            );
            return c;
        } catch (SQLiteException e){
            throw new Error("Could not load recipe from DB!");
        }
    }


    public Recipe loadRecipe(long id){

        Recipe returnRecipe;
        String recipeName;
        int recipeRating;
        int recipeAuthor;
        String recipeProceeding;

        Cursor recipeCursor  = loadRecipeCursor(id);
        if (recipeCursor != null){
            if(recipeCursor.moveToFirst()) {
                recipeName = recipeCursor.getString(recipeCursor.getColumnIndex(RecipeTable.COLUMN_NAME_NAME));
                recipeRating = recipeCursor.getInt(recipeCursor.getColumnIndex(RecipeTable.COLUMN_NAME_RATING));
                recipeAuthor = recipeCursor.getInt(recipeCursor.getColumnIndex(RecipeTable.COLUMN_NAME_AUTHORID));
                recipeProceeding = recipeCursor.getString(recipeCursor.getColumnIndex(RecipeTable.COLUMN_NAME_PROCEEDING));

            }
            else return null;
        }
        else return null;

        returnRecipe = new Recipe(recipeName, recipeRating, recipeAuthor, recipeProceeding);
        return returnRecipe;
    }**/

    /**
     * load the recipes with the given ingredients and vegetarian/vegan values from the DB
     * @param ingredients
     * @param vegetarian
     * @param vegan
     * @return
     */
    public List<Recipe> loadRecipes(List<String> ingredients, boolean vegetarian, boolean vegan){

        List<Recipe> results= new ArrayList<Recipe>();
        List<Recipe> helperList = new ArrayList<Recipe>();

        Recipe returnRecipe;
        String recipeName;
        int recipeRating;
        int recipeAuthor;
        String ingred1;
        String ingred2;
        int vegetarianValue;
        int veganValue;
        boolean isVegetarian = false;
        boolean isVegan = false;
        String recipeProceeding;
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                RecipeTable._ID,
                RecipeTable.COLUMN_NAME_NAME,
                RecipeTable.COLUMN_NAME_RATING
        };


        String selection = RecipeTable.COLUMN_NAME_VEGETARIAN + "=? AND" +
                RecipeTable.COLUMN_NAME_VEGAN + "=?";


        String []selectionArgs = {vegetarian ? "1" : "0", vegan ? "1" : "0"};

        try {
            Cursor c = myDataBase.query(
                    RecipeTable.TABLE_NAME,  // The table to query
                    null,                               // The columns to return
                    null,                                // The columns for the WHERE clause
                    null,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null                                      // The sort order
            );

            if(c!=null && c.getCount()>0) {
                if (ingredients.size()!=0) {
                    String ingredName = ingredients.get(0);
                    while (c.moveToNext()) {
                        String recipeIngred1 = c.getString(c.getColumnIndex(RecipeTable.COLUMN_NAME_INGRED1));
                        String recipeIngred2 = c.getString(c.getColumnIndex(RecipeTable.COLUMN_NAME_INGRED2));
                        if (ingredName.toUpperCase().equals(recipeIngred1.toUpperCase())||
                                ingredName.toUpperCase().equals(recipeIngred2.toUpperCase())) {


                       // if (ingredName.toUpperCase().equals(c.getString(c.getColumnIndex(RecipeTable.COLUMN_NAME_INGRED1.toUpperCase()))) ||
                         //       ingredName.toUpperCase().equals(c.getString(c.getColumnIndex(RecipeTable.COLUMN_NAME_INGRED2.toUpperCase())))) {

                            recipeName = c.getString(c.getColumnIndex(RecipeTable.COLUMN_NAME_NAME));
                            recipeRating = c.getInt(c.getColumnIndex(RecipeTable.COLUMN_NAME_RATING));
                            recipeAuthor = c.getInt(c.getColumnIndex(RecipeTable.COLUMN_NAME_AUTHORID));
                            recipeProceeding = c.getString(c.getColumnIndex(RecipeTable.COLUMN_NAME_PROCEEDING));
                            ingred1 = c.getString(c.getColumnIndex(RecipeTable.COLUMN_NAME_INGRED1));
                            ingred2 = c.getString(c.getColumnIndex(RecipeTable.COLUMN_NAME_INGRED2));
                            vegetarianValue = c.getInt(c.getColumnIndex(RecipeTable.COLUMN_NAME_VEGETARIAN));
                            veganValue = c.getInt(c.getColumnIndex(RecipeTable.COLUMN_NAME_VEGAN));

                            if(vegetarianValue == 1)
                                isVegetarian = true;
                            if(veganValue ==1)
                                isVegan = true;

                            returnRecipe = new Recipe(recipeName, recipeRating, recipeAuthor, recipeProceeding, ingred1, ingred2, isVegetarian,isVegan);
                            helperList.add(returnRecipe);

                        }
                    }
                    if (ingredients.size()>1) {
                        String ingred2Name = ingredients.get(1);
                        for(Recipe rec : helperList)
                        {
                            if(ingred2Name.toUpperCase().equals(rec.getIngred1().toUpperCase())||ingred2Name.toUpperCase().equals(rec.getIngred2().toUpperCase()))
                                results.add(rec);
                        }

                    }
                    else{
                        for(Recipe rec : helperList){
                            results.add(rec);
                        }
                    }
                } else {
                    addRecipe(c, results);
                }
            }
            return results;

        } catch (SQLiteException e){
            throw new Error("Could not load recipe from DB!");
        }
    }

    public void addRecipe(Cursor c, List<Recipe> recipeList) {

        Recipe returnRecipe;
        String recipeName;
        int recipeRating;
        int recipeAuthor;
        String ingred1;
        String ingred2;
        int vegetarian;
        int vegan;
        boolean isVegetarian = false;
        boolean isVegan = false;
        String recipeProceeding;

        while (c.moveToNext()) {
            recipeName = c.getString(c.getColumnIndex(RecipeTable.COLUMN_NAME_NAME));
            recipeRating = c.getInt(c.getColumnIndex(RecipeTable.COLUMN_NAME_RATING));
            recipeAuthor = c.getInt(c.getColumnIndex(RecipeTable.COLUMN_NAME_AUTHORID));
            recipeProceeding = c.getString(c.getColumnIndex(RecipeTable.COLUMN_NAME_PROCEEDING));
            ingred1 = c.getString(c.getColumnIndex(RecipeTable.COLUMN_NAME_INGRED1));
            ingred2 = c.getString(c.getColumnIndex(RecipeTable.COLUMN_NAME_INGRED2));
            vegetarian = c.getInt(c.getColumnIndex(RecipeTable.COLUMN_NAME_VEGETARIAN));
            vegan = c.getInt(c.getColumnIndex(RecipeTable.COLUMN_NAME_VEGAN));


            if(vegetarian == 1)
                isVegetarian = true;
            if(vegan ==1)
                isVegan = true;



            returnRecipe = new Recipe(recipeName, recipeRating, recipeAuthor, recipeProceeding, ingred1, ingred2, isVegetarian,isVegan);
            recipeList.add(returnRecipe);
        }
    }

}
