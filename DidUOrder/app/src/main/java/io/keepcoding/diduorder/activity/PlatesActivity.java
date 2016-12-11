package io.keepcoding.diduorder.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.keepcoding.diduorder.R;
import io.keepcoding.diduorder.fragment.PlateListFragment;
import io.keepcoding.diduorder.fragment.TableListFragment;
import io.keepcoding.diduorder.model.Plate;
import io.keepcoding.diduorder.model.Table;

public class PlatesActivity extends AppCompatActivity implements PlateListFragment.OnPlateSelectedListener {

    public static final String TAG = PlatesActivity.class.getName();
    public static final String EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_plates);

        // Get fragment manager
        FragmentManager fm = getFragmentManager();

        // Check plate list fragment
        if (fm.findFragmentById(R.id.fragment_plate_list) == null) {
            // Le paso la ciudad que quiere el usuario cargar
            int tableIndex = getIntent().getIntExtra(EXTRA_TABLE_INDEX, 0);

            // Creo el fragment pasándole los argumentos
            PlateListFragment plateListFragment = PlateListFragment.newInstance(tableIndex);

            fm.beginTransaction()
                    .add(R.id.fragment_plate_list, plateListFragment)
                    .commit();
        }
    }


    @Override
    public void onPlateSelected(Plate plate, int position) {

        Log.v(TAG, "Selected plate in position " + position);
    }
}
