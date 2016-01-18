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
 * Helper Class to perform database operations
 */

public class DBHelper extends SQLiteOpenHelper {

    //patch to the gadgets internal database
    private static String DB_PATH = "/data/data/com.fridge.tobi.fridgerator/databases/";

    //name of the database
    private static String DB_NAME = "ingredients";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    public DBHelper (Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    /**
     * Create the internal database and fill it with data from a file which has been created using an XMl Editor
     * @throws IOException
     */
    public void createDataBase() throws IOException {


        //if Path to internal database does not exist, create it
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
                //write data from the file into database
                copyDataBase();
            }
            catch (IOException e) {
                throw new Error ("Error copying database");
            }
      //  }
    }

/**
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
    }**/

    /**
     * Write information form a file into the database
     * @throws IOException
     */
    private void copyDataBase() throws IOException{

        //InputSteam is the file created with the XML Editor. It is stored in the assets folder
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        //outFileName is the gadgets internal database
        String outFileName = DB_PATH + DB_NAME;

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        //Write data from file into the database
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    /**
     * open the internal database
     * @throws SQLException
     */
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
     * Load the recipes with the given ingredients and vegetarian/vegan values from the DB
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

        String selection;
        String []selectionArgs;

        //if only checkBox vegetarian set, do not use filter vegan
        if(vegetarian && !vegan){
            selection = RecipeTable.COLUMN_NAME_VEGETARIAN + "=?";
            selectionArgs =  new String[1];
            selectionArgs[0]= "1";
        }

        //if only checkBox vegan set, do not use filter vegetarian
        else if(!vegetarian && vegan){
            selection = RecipeTable.COLUMN_NAME_VEGAN + "=?";
            selectionArgs =  new String[1];
            selectionArgs[0]= "1";
        }

        //if no checkbox is set, do not use filter
        else if(!vegetarian && !vegan){
            selection = null;
            selectionArgs = null;

        }
        //if both checkboxes are set, use both filter
        else{
            selection = RecipeTable.COLUMN_NAME_VEGETARIAN + "=? AND " +
                    RecipeTable.COLUMN_NAME_VEGAN + "=?";
            selectionArgs =  new String[2];
            selectionArgs[0]= "1";
            selectionArgs[1]= "1";
        }


        //Perform SQL query using the projection and selection arguments
        try {
            Cursor c = myDataBase.query(
                    RecipeTable.TABLE_NAME,             // The table to query
                    null,                               // The columns to return
                    selection,                                // The columns for the WHERE clause
                    selectionArgs,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                       // don't filter by row groups
                    null                                      // The sort order
            );

            // does the returned cursor have entrys?
            if(c!=null && c.getCount()>0) {
                //are there ingredients to filter the recipe rows in the cursor? If yes, check if recipes have the ingredient
                if (ingredients.size()!=0) {
                    String ingredName = ingredients.get(0);
                    //iterate over the cursor rows and get the recipe data
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

                            //create a recipe object and add it to a list
                            returnRecipe = new Recipe(recipeName, recipeRating, recipeAuthor, recipeProceeding, ingred1, ingred2, isVegetarian,isVegan);
                            helperList.add(returnRecipe);

                        }
                    }
                    // there are more ingredients to filter the results
                    if (ingredients.size()>1) {
                        String ingred2Name = ingredients.get(1);
                        for(Recipe rec : helperList)
                        {
                            //check if loaded recipes have the necessary ingredient
                            if(ingred2Name.toUpperCase().equals(rec.getIngred1().toUpperCase())||ingred2Name.toUpperCase().equals(rec.getIngred2().toUpperCase()))
                                results.add(rec);
                        }

                    }
                    else{
                        for(Recipe rec : helperList){
                            results.add(rec);
                        }
                    }
                    // If no ingredients to filter, load all recipes
                } else {
                    addRecipe(c, results);
                }
            }
            //return list with loaded recipes
            return results;

        } catch (SQLiteException e){
            throw new Error("Could not load recipe from DB!");
        }
    }

    /**
     * gets recipes from a Cursor row and adds them to a list
     * @param c
     * @param recipeList
     */
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
