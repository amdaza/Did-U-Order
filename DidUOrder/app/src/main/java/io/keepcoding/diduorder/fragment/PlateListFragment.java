package io.keepcoding.diduorder.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import io.keepcoding.diduorder.R;
import io.keepcoding.diduorder.model.Plate;
import io.keepcoding.diduorder.model.Plates;

public class PlateListFragment extends Fragment {

    private OnPlateSelectedListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_plate_list, container, false);

        // Access to ListView
        ListView listView = (ListView) root.findViewById(android.R.id.list);

        // Create model
        Plates plates = new Plates();

        // Create adapter to link model with list
        ArrayAdapter<Plate> adapter = new ArrayAdapter<Plate>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                plates.getPlates()
        );

        // Assign adapter to list
        listView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPlateSelectedListener) {
            mListener = (OnPlateSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnPlateSelectedListener {
        void onPlateSelected(Plate plate, int position);
    }
}
