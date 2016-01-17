package com.fridge.tobi.fridgerator.Model;

/**
 * Created by Tobi on 03.01.2016.
 */
public class Recipe {

    private long id;
    private String name;
    private int rating;
    private int authorId;
    private String proceeding;
    private String ingred1;
    private String  ingred2;
    private boolean vegetarian;
    private boolean vegan;


    public long getId() {return id;}
    public String getName(){return name;}
    public int getRating () {return rating;}
    public int getAuthorId (){return authorId;}
    public String getProceeding(){return proceeding;}
    public String getIngred1() {return ingred1;}
    public String getIngred2() {return  ingred2;}
    public boolean isVegetarian() {return vegetarian;}
    public boolean isVegan() {return vegan;}

    public Recipe(String Name, int Rating, int AuthorID, String Proceeding,String Ingred1, String Ingred2, boolean Vegetarian,
                  boolean Vegan){
        name = Name;
        rating = Rating;
        authorId = AuthorID;
        proceeding = Proceeding;
        ingred1 = Ingred1;
        ingred2 = Ingred2;
        vegetarian = Vegetarian;
        vegan = Vegan;
    }

    public String toString() {return getName();}
}
