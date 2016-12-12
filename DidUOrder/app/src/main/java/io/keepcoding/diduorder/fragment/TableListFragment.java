package io.keepcoding.diduorder.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import io.keepcoding.diduorder.R;
import io.keepcoding.diduorder.model.Table;
import io.keepcoding.diduorder.model.Tables;

/**
 * Created by Alicia on 11/12/2016.
 */

public class TableListFragment extends Fragment {

    private OnTableSelectedListener mOnTableSelectedListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_table_list, container, false);

        // Access to ListView
        ListView listView = (ListView) root.findViewById(android.R.id.list);

        // Create model
        String tableName = getResources().getString(R.string.table);
        final Tables tables = new Tables(tableName, 10);

        // Create adapter to link model with list
        ArrayAdapter<Table> adapter = new ArrayAdapter<Table>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                tables.getTables()
        );

        // Assign adapter to list
        listView.setAdapter(adapter);

        // Assign listener to list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Notify listener -> row selected
                if (mOnTableSelectedListener != null) {
                    mOnTableSelectedListener.onTableSelected(tables.getTable(position), position);
                }
            }
        });

        FloatingActionButton addButton = (FloatingActionButton) root.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getView(), "Añadir mesa no implementado =(", Snackbar.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof  OnTableSelectedListener) {
            mOnTableSelectedListener = (OnTableSelectedListener) getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mOnTableSelectedListener = null;
    }

    public interface OnTableSelectedListener {
        void onTableSelected(Table table, int position);
    }
}
