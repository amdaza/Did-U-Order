package io.keepcoding.diduorder.model;

import java.util.ArrayList;

/**
 * Created by Alicia on 11/12/2016.
 */

public class Tables {
    private ArrayList mTables;

    public Tables(String baseName, int quantity){
        mTables = new ArrayList<>(quantity);
        for (int i=0; i<quantity; i++){
            Table table = new Table(baseName, i);
            mTables.add(i, table);
        }
    }

    public ArrayList getTables() {
        return mTables;
    }
}
