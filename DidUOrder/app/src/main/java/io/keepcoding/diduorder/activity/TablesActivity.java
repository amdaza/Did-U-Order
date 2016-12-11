package io.keepcoding.diduorder.activity;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.keepcoding.diduorder.R;
import io.keepcoding.diduorder.fragment.TableListFragment;

public class TablesActivity extends AppCompatActivity {

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
}
