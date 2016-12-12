package io.keepcoding.diduorder.model;

import java.util.LinkedList;

/**
 * Created by Alicia on 11/12/2016.
 */

public class Plates {
    private LinkedList<Plate> mPlates;

    public Plates() {
        mPlates = new LinkedList<>();
    }

    public LinkedList<Plate> getPlates() {
        return mPlates;
    }

    public void setPlates(LinkedList<Plate> plates) {
        mPlates = plates;
    }

    public Plate getPlate(int position) {
        return mPlates.get(position);
    }

    public void addPlate(Plate plate){
        mPlates.add(plate);
    }

    public int getCount() {
        return mPlates.size();
    }
}
