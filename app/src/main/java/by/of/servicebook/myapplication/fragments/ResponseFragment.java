package by.of.servicebook.myapplication.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.of.servicebook.myapplication.MainActivity;
import by.of.servicebook.myapplication.R;
import by.of.servicebook.myapplication.utils.AppConst;


public class ResponseFragment extends Fragment {
    private Activity activity;

    public static ResponseFragment newInstance() {
        return new ResponseFragment();
    }

    public ResponseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        ((MainActivity) activity).onSectionAttached(AppConst.FRAGMENT_RESPONSE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_response, container, false);
    }


}
