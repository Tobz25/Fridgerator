package com.fridge.tobi.fridgerator.Database;

import android.provider.BaseColumns;

/**
 * Defines how the database and its tables are constructed
 */
public class SavedDataContract {

    public SavedDataContract(){}

    /* Definition of the table ingredients */
    public static abstract class IngredientTable implements BaseColumns {
        public static final String TABLE_NAME = "ingredient";
       // public static final String COLUMN_NAME_INGREDIENT_ID = "ingredientid";
        public static final String COLUMN_NAME_NAME ="ingredientname";
        public static final String COLUMN_NAME_TYP = "ingredienttyp";
        public static final String COLUMN_NAME_VEGETARIAN = "ingredientvegetarian";
        public static final String COLUMN_NAME_VEAGN ="ingredientvegan";
    }

    //Definition of the recipe table
    public static abstract class RecipeTable implements BaseColumns {
        public static final String TABLE_NAME = "recipe";
      //  public static final String COLUMN_NAME_RECIPE_ID = "recipeid";
        public static final String COLUMN_NAME_NAME = "recipename";
        public static final String COLUMN_NAME_RATING = "reciperating";
        public static final String COLUMN_NAME_COMMENT = "recipecomment";
        public static final String COLUMN_NAME_AUTHORID = "recipeauthor";
        public static final String COLUMN_NAME_VEGETARIAN = "vegetarian";
        public static final String COLUMN_NAME_VEGAN = "vegan";
        public static final String COLUMN_NAME_PROCEEDING = "recipeproceeding";
        public static final String COLUMN_NAME_INGRED1 = "ingred1";
        public static final String COLUMN_NAME_INGRED2 = "ingred2";
    }
    //Definition of the table connecting recipes and ingredients
    public static abstract class IngredientsInRecipeTable implements BaseColumns {
        public static final String TABLE_NAME = "ingredients_in_recipe";
        public static final String COLUMN_NAME_INGREDIENTID = "ingredient_id";
        public static final String COLUMN_NAME_RECIPEID = "recipe_id";
        public static final String COLUMN_NAME_QUANTITY = "quantity";
        public static final String COLUMN_NAME_UNIT = "unit";
    }

}
