package io.keepcoding.diduorder.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.keepcoding.diduorder.R;
import io.keepcoding.diduorder.fragment.PlateListFragment;
import io.keepcoding.diduorder.fragment.TableListFragment;
import io.keepcoding.diduorder.model.Table;

public class TablesActivity extends AppCompatActivity implements TableListFragment.OnTableSelectedListener {

    public static final String TAG = TablesActivity.class.getName();
    //private int tablePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        // Cargamos a mano el fragment
        FragmentManager fm = getFragmentManager();

        // Preguntamos a ver si existe el hueco para CityList
        if (findViewById(R.id.fragment_table_list) != null) {
            // Existe hueco, y habiendo hueco metemos el fragment de CityList
            if (fm.findFragmentById(R.id.fragment_table_list) == null) {
                fm.beginTransaction()
                        .add(R.id.fragment_table_list, new TableListFragment())
                        .commit();
            }
        }
    }

    @Override
    public void onTableSelected(Table table, int position) {
        Log.v(TAG, "Selected table in position " + position);

        // Check if fragment is already in interface
        FragmentManager fragmentManager = getFragmentManager();
        PlateListFragment plateListFragment =
                (PlateListFragment) fragmentManager.findFragmentById(R.id.fragment_plate_list);

        if (plateListFragment != null) {
            // Already created
            Log.v(TAG, "Plate already created in " + position);
            plateListFragment.changeTablePosition(position);
        } else {
            Log.v(TAG, "New intent plate created in " + position);
            Intent intent = new Intent(this, PlatesActivity.class);

            intent.putExtra(PlatesActivity.EXTRA_TABLE_INDEX, position);

            startActivity(intent);
        }
    }
}
