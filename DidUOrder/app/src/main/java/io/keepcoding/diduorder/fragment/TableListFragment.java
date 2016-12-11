package io.keepcoding.diduorder.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import io.keepcoding.diduorder.R;
import io.keepcoding.diduorder.model.Plate;
import io.keepcoding.diduorder.model.Plates;
import io.keepcoding.diduorder.model.Table;
import io.keepcoding.diduorder.model.Tables;

/**
 * Created by Alicia on 11/12/2016.
 */

public class TableListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_table_list, container, false);

        // Access to ListView
        ListView listView = (ListView) root.findViewById(android.R.id.list);

        // Create model
        String tableName = getResources().getString(R.string.table);
        Tables tables = new Tables(tableName, 10);

        // Create adapter to link model with list
        ArrayAdapter<Table> adapter = new ArrayAdapter<Table>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                tables.getTables()
        );

        // Assign adapter to list
        listView.setAdapter(adapter);

        return root;
    }
}
