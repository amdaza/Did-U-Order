package io.keepcoding.diduorder.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.keepcoding.diduorder.R;
import io.keepcoding.diduorder.fragment.PlateListFragment;
import io.keepcoding.diduorder.model.Plate;

public class PlatesActivity extends AppCompatActivity implements PlateListFragment.OnPlateSelectedListener{

    public static final String TAG = PlatesActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_plates);


    }

    @Override
    public void onPlateSelected(Plate plate, int position) {
        Log.v(TAG, "Selected plate in position " + position);

    }
}
