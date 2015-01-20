package by.of.servicebook.myapplication.fragments;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import by.of.servicebook.myapplication.MainActivity;
import by.of.servicebook.myapplication.R;
import by.of.servicebook.myapplication.utils.AppConst;


public class AboutFragment extends Fragment implements View.OnClickListener {
    private Activity activity;

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        ((MainActivity) activity).onSectionAttached(AppConst.FRAGMENT_ABOUT);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);
        //init views
        ImageView ivPhone = (ImageView) rootView.findViewById(R.id.iv_phone);
        ImageView ivAddress = (ImageView) rootView.findViewById(R.id.iv_address);
        TextView tvPhone = (TextView) rootView.findViewById(R.id.tv_phone);
        TextView tvAddress = (TextView) rootView.findViewById(R.id.tv_address);
        ImageView ivEmail = (ImageView) rootView.findViewById(R.id.iv_email);
        ImageView ivSite = (ImageView) rootView.findViewById(R.id.iv_site);
        TextView tvEmail = (TextView) rootView.findViewById(R.id.tv_email);
        TextView tvSite = (TextView) rootView.findViewById(R.id.tv_site);
        ivPhone.setOnClickListener(this);
        ivAddress.setOnClickListener(this);
        tvPhone.setOnClickListener(this);
        tvAddress.setOnClickListener(this);
        ivEmail.setOnClickListener(this);
        ivSite.setOnClickListener(this);
        tvEmail.setOnClickListener(this);
        tvSite.setOnClickListener(this);
        return rootView;

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.tv_phone:
            case R.id.iv_phone:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+375447971575"));
                break;
            case R.id.tv_address:
            case R.id.iv_address:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:0,0?q=Минск + пр.Пушкина + 70A"));
                break;
            case R.id.tv_email:
            case R.id.iv_email:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"gulevichpavel@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "example send email");
                break;
            case R.id.tv_site:
            case R.id.iv_site:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://servicebook.of.by"));
                break;
        }
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
