package io.keepcoding.diduorder.activity;

import android.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import io.keepcoding.diduorder.R;
import io.keepcoding.diduorder.fragment.PlateListFragment;
import io.keepcoding.diduorder.model.Plate;

public class PlatesActivity extends AppCompatActivity implements PlateListFragment.OnPlateSelectedListener {

    public static final String TAG = PlatesActivity.class.getName();
    public static final String EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_plates);

        // Custom toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.todays_menu);
        }

        // Get fragment manager
        FragmentManager fm = getFragmentManager();

        // Check plate list fragment
        if (fm.findFragmentById(R.id.fragment_plate_list) == null) {
            // Pass table index
            int tableIndex = getIntent().getIntExtra(EXTRA_TABLE_INDEX, 0);

            // Create fragmsent with arguments
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superValue = super.onOptionsItemSelected(item);

        if (item.getItemId() == android.R.id.home) {
            // Back arrow tapped -> Finalize activity
            finish();
            return true;
        }

        return superValue;
    }
}
