package by.of.servicebook.myapplication.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import by.of.servicebook.myapplication.MainActivity;
import by.of.servicebook.myapplication.R;
import by.of.servicebook.myapplication.utils.AppConst;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GarageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GarageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GarageFragment extends Fragment implements View.OnClickListener {
    private Activity activity;
    private CheckBox chb1, chb2;
    SharedPreferences sharedPreferences;


    // TODO: Rename and change types and number of parameters
    public static GarageFragment newInstance() {
        GarageFragment fragment = new GarageFragment();

        return fragment;
    }

    public GarageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_garage, container, false);
        chb1 =(CheckBox) v.findViewById(R.id.chb1);
        chb2 =(CheckBox) v.findViewById(R.id.chb2);
        chb1.setOnClickListener(this);
        chb2.setOnClickListener(this);
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        int car = sharedPreferences.getInt(AppConst.VEHICLE, -1);
        if (car==-1){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(AppConst.VEHICLE, 1);
            editor.apply();
            car = 1;
        }

        if (car==1){
            chb1.setChecked(true);
            chb2.setChecked(false);
        } else {
            chb1.setChecked(false);
            chb2.setChecked(true);
        }

        LinearLayout ll_1 = (LinearLayout) v.findViewById(R.id.car1);
        LinearLayout ll_2 = (LinearLayout) v.findViewById(R.id.car2);

        ll_1.setOnClickListener(this);
        ll_2.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.chb1:
                if (chb1.isChecked()) {
                    chb1.setChecked(true);
                    chb2.setChecked(false);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(AppConst.VEHICLE, 1);
                    editor.apply();
                } else {
                    chb1.setChecked(true);
                }
                break;
            case R.id.chb2:
                if (chb2.isChecked()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(AppConst.VEHICLE, 2);
                    editor.apply();
                    chb1.setChecked(false);
                    chb2.setChecked(true);
                } else {
                    chb2.setChecked(true);
                }
                break;
            case R.id.car1:
                break;
            case R.id.car2:
                break;

        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        ((MainActivity) activity).onSectionAttached(AppConst.FRAGMENT_GARAGE);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
