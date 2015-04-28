package by.of.servicebook.myapplication.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import by.of.servicebook.myapplication.R;
import by.of.servicebook.myapplication.activities.CarDetailsActivity;
import by.of.servicebook.myapplication.activities.MainActivity;
import by.of.servicebook.myapplication.adapters.CarsAdapter;
import by.of.servicebook.myapplication.db.models.Car;
import by.of.servicebook.myapplication.db.DataProvider;
import by.of.servicebook.myapplication.utils.AppConst;

/**
 * Created by Pavel on 21.04.2015.
 */
public class GarageFragment extends Fragment {

    private Activity activity;
    CarsAdapter adapter;
    SharedPreferences sharedPreferences;

    public static GarageFragment newInstance() {
        GarageFragment fragment = new GarageFragment();

        return fragment;
    }

    public GarageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        ((MainActivity) activity).onSectionAttached(AppConst.FRAGMENT_GARAGE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_newgarage, container, false);

        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        int car = sharedPreferences.getInt(AppConst.VEHICLE, -1);
        if (car==-1){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(AppConst.VEHICLE, 1);
            editor.apply();
        }

        ListView lvCars = (ListView) v.findViewById(R.id.lvCars);

        List<Car> cars = DataProvider.getInstance().getAllCars();

        adapter = new CarsAdapter(getActivity(), R.layout.item_car, cars, sharedPreferences);

        lvCars.setAdapter(adapter);
        lvCars.setOnItemClickListener(onItemClickListener);

        return v;
    }

    ListView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int carId = adapter.getItem(position).key;

            CarDetailsActivity.launch(getActivity(), carId);
        }
    };


}
