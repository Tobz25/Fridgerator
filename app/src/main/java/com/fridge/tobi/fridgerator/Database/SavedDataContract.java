package com.fridge.tobi.fridgerator.Database;

import android.provider.BaseColumns;

/**
 * Created by Tobi on 17.12.2015.
 */
public class SavedDataContract {

    public SavedDataContract(){}

    /* Definition of the table contents */
    public static abstract class IngredientTable implements BaseColumns {
        public static final String TABLE_NAME = "ingredient";
       // public static final String COLUMN_NAME_INGREDIENT_ID = "ingredientid";
        public static final String COLUMN_NAME_NAME ="ingredientname";
        public static final String COLUMN_NAME_TYP = "ingredienttyp";
        public static final String COLUMN_NAME_VEGETARIAN = "ingredientvegetarian";
        public static final String COLUMN_NAME_VEAGN ="ingredientvegan";
    }

    public static abstract class RecipeTable implements BaseColumns {
        public static final String TABLE_NAME = "recipe";
      //  public static final String COLUMN_NAME_RECIPE_ID = "recipeid";
        public static final String COLUMN_NAME_NAME = "recipename";
        public static final String COLUMN_NAME_RATING = "reciperating";
        public static final String COLUMN_NAME_COMMENT = "recipecomment";
        public static final String COLUMN_NAME_AUTHORID = "recipeauthor";
        public static final String COLUMN_NAME_PROCEEDING = "recipeproceeding";
    }

    public static abstract class IngredientsInRecipeTable implements BaseColumns {
        public static final String TABLE_NAME = "ingredients_in_recipe";
        public static final String COLUMN_NAME_INGREDIENTID = "ingredient_id";
        public static final String COLUMN_NAME_RECIPEID = "recipe_id";
        public static final String COLUMN_NAME_QUANTITY = "quantity";
        public static final String COLUMN_NAME_UNIT = "unit";
    }

}
