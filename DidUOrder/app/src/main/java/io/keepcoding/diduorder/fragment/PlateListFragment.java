package io.keepcoding.diduorder.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ViewSwitcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

import io.keepcoding.diduorder.R;
import io.keepcoding.diduorder.model.Plate;
import io.keepcoding.diduorder.model.Plates;

public class PlateListFragment extends Fragment {

    public static final String TAG = PlateListFragment.class.getName();

    private OnPlateSelectedListener mListener;
    private static final String ARG_TABLE_INDEX = "ARG_TABLE_INDEX";
    private static final int LOADING_VIEW_INDEX = 0;
    private static final int PLATE_VIEW_INDEX = 1;

    private static Bundle arguments;

    private ViewSwitcher mViewSwitcher;
    private RecyclerView mList;
    private ListView mListView;

    // Model
    //private int mTableIndex;
    private Plates mPlatesList;

    public static PlateListFragment newInstance(int tableIndex) {
        arguments = new Bundle();
        arguments.putInt(ARG_TABLE_INDEX, tableIndex);

        PlateListFragment plateListFragment = new PlateListFragment();
        plateListFragment.setArguments(arguments);

        return plateListFragment;
    }

    public void changeTablePosition(int position){
        arguments.putInt(ARG_TABLE_INDEX, position);
    }



    private void setPlates(Plates plates){
        if (mPlatesList.getCount() == 0){
            downloadPlatesList();

            Log.v(TAG, "Call to downloadPlatesList");
        } else {
            Log.v(TAG, "Else plates setted");
            mViewSwitcher.setDisplayedChild(PLATE_VIEW_INDEX);

            // Create adapter to link model with list
            ArrayAdapter<Plate> adapter = new ArrayAdapter<Plate>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    mPlatesList.getPlates()
            );

            // Assign adapter to list
            mListView.setAdapter(adapter);
        }
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        // Create model
        mPlatesList = new Plates();

/*
        // Get arguments from model
        if (getArguments() != null) {
           // mTable = (Table) getArguments().getSerializable(ARG_TABLE);
        }
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_plate_list, container, false);

        // Access to ListView
        mListView = (ListView) root.findViewById(R.id.list_view_plate);
        
        mViewSwitcher = (ViewSwitcher) root.findViewById(R.id.view_switcher);
        mViewSwitcher.setInAnimation(getActivity(), android.R.anim.fade_in);
        mViewSwitcher.setOutAnimation(getActivity(), android.R.anim.fade_out);

        setPlates(mPlatesList);

        // Create adapter to link model with list
        ArrayAdapter<Plate> adapter = new ArrayAdapter<Plate>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mPlatesList.getPlates()
        );

        // Assign adapter to list
        mListView.setAdapter(adapter);

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

    private void downloadPlatesList() {
        AsyncTask<Void, Integer, Plates> menuDownloader = new AsyncTask<Void, Integer, Plates>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                // Show loading view
                mViewSwitcher.setDisplayedChild(LOADING_VIEW_INDEX);
            }

            @Override
            protected Plates doInBackground(Void... params) {
                URL url;
                InputStream input;

                try {
                    // Download data
                    url = new URL("http://www.mocky.io/v2/584df1210f0000f424d40fc3");

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.connect();
                    int responseLength = con.getContentLength();
                    byte data[] = new byte[1024];
                    long currentBytes = 0;
                    int downloadedBytes;
                    input = con.getInputStream();
                    StringBuilder sb = new StringBuilder();
                    while ((downloadedBytes = input.read(data)) != -1 && !isCancelled()) {
                        sb.append(new String(data, 0, downloadedBytes));

                        publishProgress((int)(currentBytes * 100) / responseLength);
                    }

                    if (isCancelled()) {
                        // Do something
                        return null;
                    }

                    // Get JSON data
                    JSONObject jsonRoot = new JSONObject(sb.toString());
                    JSONArray platesJSON = jsonRoot.getJSONArray("plates");
                    Plates platesTemp = new Plates();

                    for(int i=0; i<platesJSON.length(); i++){
                        JSONObject plateJSON = platesJSON.getJSONObject(i);
                        //String name, LinkedList<String> ingredients, String imageResourceName, int price
                        String name = plateJSON.getString("name");

                        JSONArray ingredientsJSON = plateJSON.getJSONArray("ingredients");
                        LinkedList<String> ingredients = new LinkedList<>();
                        for(int j=0; j<ingredientsJSON.length(); j++){
                            ingredients.add(ingredientsJSON.getString(j));
                        }

                        String imageResourceName = plateJSON.getString("image");
                        int price = plateJSON.getInt("price");

                        Plate plate = new Plate(name, ingredients, imageResourceName, price);

                        platesTemp.addPlate(plate);
                    }

                    return platesTemp;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(Plates plates) {
                super.onPostExecute(plates);

                if (plates != null) {
                    mPlatesList.setPlates(plates.getPlates());

                    // Actualizo la interfaz
                    setPlates(plates);
                } else {
                    // Error, notify user
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                    alertDialog.setTitle(R.string.error);
                    alertDialog.setMessage(R.string.plates_download_error_message);
                    alertDialog.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            downloadPlatesList();
                        }
                    });

                    alertDialog.show();
                }
            }
        };

        menuDownloader.execute();
    }
}
