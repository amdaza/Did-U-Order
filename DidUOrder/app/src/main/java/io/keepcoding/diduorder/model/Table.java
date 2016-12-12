package io.keepcoding.diduorder.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;


/**
 * Created by Alicia on 11/12/2016.
 */

public class Table implements Serializable{
    private String mBaseName;
    private int mPosition;
    private HashMap<Plate, Integer> mPlates;

    public Table(String baseName, int position){
        mBaseName = baseName;
        mPosition = position;
        mPlates = new LinkedHashMap<>();
    }

    public Table emptyTable(){
        mPlates = new LinkedHashMap<>();
        return this;
    }

    public Table addPlate(Plate plate){
        int plateCount = mPlates.get(plate);
        mPlates.put(plate, plateCount + 1);
        return this;
    }

    public String toString(){
        String name = mBaseName.substring(0, 1).toUpperCase() + mBaseName.substring(1);
        return name + " " + (mPosition + 1);
    }
}
