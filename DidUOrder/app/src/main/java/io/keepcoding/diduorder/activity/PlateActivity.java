package io.keepcoding.diduorder.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import io.keepcoding.diduorder.R;
import io.keepcoding.diduorder.fragment.PlateFragment;

/**
 * Created by Alicia on 11/12/2016.
 */

public class PlateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Set toolbar as used
        setSupportActionBar(toolbar);

        // Get fragment
        FragmentManager fragmentManager = getFragmentManager();

        // Check if there's place for Plate
        if (findViewById(R.id.fragment_plate) != null) {
            // Existe el hueco, y habiendo hueco metemos el fragment del CityPager
            if (fragmentManager.findFragmentById(R.id.fragment_plate) == null) {
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_plate, new PlateFragment())
                        .commit();
            }
        }
    }
}
