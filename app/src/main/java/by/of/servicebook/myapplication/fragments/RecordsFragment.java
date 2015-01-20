package by.of.servicebook.myapplication.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import by.of.servicebook.myapplication.MainActivity;
import by.of.servicebook.myapplication.R;
import by.of.servicebook.myapplication.adapters.RecordsAdapter;
import by.of.servicebook.myapplication.models.Job;
import by.of.servicebook.myapplication.models.Record;
import by.of.servicebook.myapplication.providers.DataProvider;
import by.of.servicebook.myapplication.utils.AppConst;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings("ALL")
public class RecordsFragment extends Fragment {
    Activity activity;
    DataProvider provider = DataProvider.getInstance();
    RecordsAdapter adapter;
    SharedPreferences sharedPreferences;

    public static RecordsFragment newInstance () {
        return new RecordsFragment();
    }

    public RecordsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        ((MainActivity) activity).onSectionAttached(AppConst.FRAGMENT_RECORDS);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_records, container, false);

        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        int car_id = sharedPreferences.getInt(AppConst.VEHICLE, 1);
        TextView tvCar = (TextView) rootView.findViewById(R.id.car);
        if (car_id == 1){
            tvCar.setText(getText(R.string.car_1));
        } else {
            tvCar.setText(getText(R.string.car_2));
        }

        ListView listView = (ListView) rootView.findViewById(R.id.listView);
        List<Record> records = provider.getRecordsByCarId(car_id);
        adapter = new RecordsAdapter(getActivity(), records);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int record_id = ((Record)parent.getItemAtPosition(position)).key;
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.container, DetailRecordFragment.newInstance(record_id))
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;

    }



}
