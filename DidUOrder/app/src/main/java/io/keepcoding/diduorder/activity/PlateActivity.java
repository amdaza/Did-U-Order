package io.keepcoding.diduorder.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import io.keepcoding.diduorder.R;
import io.keepcoding.diduorder.fragment.PlateFragment;
import io.keepcoding.diduorder.model.Plate;

/**
 * Created by Alicia on 11/12/2016.
 */

public class PlateActivity extends AppCompatActivity {

    public static final String EXTRA_PLATE = "EXTRA_PLATE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Set toolbar as used
        setSupportActionBar(toolbar);

        // Get fragment
        FragmentManager fragmentManager = getFragmentManager();

        if (fragmentManager.findFragmentById(R.id.fragment_plate) == null) {

            Plate plate = (Plate) getIntent().getSerializableExtra(EXTRA_PLATE);

            PlateFragment plateFragment = PlateFragment.newInstance(plate);

            fragmentManager.beginTransaction()
                    .add(R.id.fragment_plate, plateFragment)
                    .commit();
        }
    }
}
