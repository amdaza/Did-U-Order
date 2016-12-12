package io.keepcoding.diduorder.model;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by Alicia on 11/12/2016.
 */

public class Plate implements Serializable{
    private String mName;
    private LinkedList<String> mIngredients;
    private String mImageResourceName;
    private int mPrice;

    public Plate(String name, LinkedList<String> ingredients, String imageResourceName, int price) {
        mName = name;
        mIngredients = ingredients;
        mImageResourceName = imageResourceName;
        mPrice = price;
    }
    public Plate(String name) {
        this(name, null, null, 0);
    }

    public LinkedList<String> getIngredients() {
        return mIngredients;
    }

    public void setIngredients(LinkedList<String> ingredients) {
        mIngredients = ingredients;
    }

    public String getImageResourceName() {
        return mImageResourceName;
    }

    public void setImageResourceName(String imageResourceName) {
        mImageResourceName = imageResourceName;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int price) {
        mPrice = price;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getIngredientListString() {
        String result = "";

        for (String ingredient: mIngredients) {
            result = result.concat(ingredient + "\n");
        }

        return result;
    }
}
