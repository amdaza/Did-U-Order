package io.keepcoding.diduorder.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import io.keepcoding.diduorder.R;
import io.keepcoding.diduorder.model.Plate;

/**
 * Created by Alicia on 11/12/2016.
 */

public class PlateFragment extends Fragment{

    public static final String TAG = PlateFragment.class.getCanonicalName();

    private static final String ARG_PLATE = "ARG_PLATE";

    // Model
    private Plate mPlate;

    private TextView mPlateTextView;
    private TextView mIngredientsTextView;
    private ImageView mPlateImageView;

    public static PlateFragment newInstance(Plate plate) {
        PlateFragment fragment = new PlateFragment();

        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_PLATE, plate);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        // Saco el modelo de los argumentos
        if (getArguments() != null) {
            mPlate = (Plate) getArguments().getSerializable(ARG_PLATE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_plate, container, false);

        mPlateTextView = (TextView) root.findViewById(R.id.plate_name);
        mIngredientsTextView = (TextView) root.findViewById(R.id.ingredients);
        mPlateImageView = (ImageView) root.findViewById(R.id.plate_image);

        mPlateTextView.setText(mPlate.getName());
        mIngredientsTextView.setText(mPlate.getIngredientListString());

        String icon = mPlate.getImageResourceName();
        int imageResource = getResources().getIdentifier(icon, "drawable", mPlateImageView.getContext().getPackageName());
        mPlateImageView.setImageResource(imageResource);

        return root;
    }
}
