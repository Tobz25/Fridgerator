package com.fridge.tobi.fridgerator.Model;

/**
 * Created by Tobi on 13.01.2016.
 */
public class Ingredient {

    private long id;
    private String name;
    private String type;
    private boolean vegetarian;
    private boolean vegan;

    public long getId() {return id;}
    public String getName() {return name;}
    public String getType() {return type;}
    public boolean isVegetarian(){return vegetarian;}
    public boolean isVegan() {return vegan;}

    public Ingredient(String Name, String Type, boolean Vegetarian, boolean Vegan){
        name = Name;
        type = Type;
        vegetarian = Vegetarian;
        vegan = Vegan;
    }

}
