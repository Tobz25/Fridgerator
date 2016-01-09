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

    public long getID() {return id;}
    public String getName(){return name;}
    public int getRating () {return rating;}
    public int getAuthorId (){return authorId;}
    public String getProceeding(){return proceeding;}

    public Recipe(String Name, int Rating, int AuthorID, String Proceeding){
        name = Name;
        rating = Rating;
        authorId = AuthorID;
        proceeding = Proceeding;
    }
}
