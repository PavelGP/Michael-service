package by.of.servicebook.myapplication.fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import by.of.servicebook.myapplication.activities.MainActivity;
import by.of.servicebook.myapplication.R;
import by.of.servicebook.myapplication.activities.SplashActivity;
import by.of.servicebook.myapplication.activities.TermsOfUseActivity;
import by.of.servicebook.myapplication.utils.AppConst;


public class SettingsFragment extends Fragment implements View.OnClickListener {
    private Activity activity;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        ((MainActivity) activity).onSectionAttached(AppConst.FRAGMENT_SETTINGS);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return v;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.rlLogout:
                showLogoutDialog();
                break;
            case R.id.rlTermsOfUse:
                TermsOfUseActivity.launch(getActivity());
                break;
            case R.id.rlWriteUs:
                sendEmail();
                break;
        }
    }

    private void showLogoutDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.warning_change_user))
                .setCancelable(false)
                .setNegativeButton(R.string.no,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                doLogout();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void doLogout(){

        ParseUser.logOutInBackground(new LogOutCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    SplashActivity.launch(getActivity());
                } else {
                    Toast.makeText(getActivity(), getString(R.string.logout_error),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendEmail(){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{getString(R.string.project_email)});
        i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
        try {
            startActivity(Intent.createChooser(i, getString(R.string.choose_email_client)));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), getString(R.string.no_email_clients), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private class ViewHolder{
        RelativeLayout rlLogout, rlTermsOfUse, rlWriteUs;

        public ViewHolder(View v){
            rlLogout = (RelativeLayout) v.findViewById(R.id.rlLogout);
            rlTermsOfUse = (RelativeLayout) v.findViewById(R.id.rlTermsOfUse);
            rlWriteUs = (RelativeLayout) v.findViewById(R.id.rlWriteUs);

            rlLogout.setOnClickListener(SettingsFragment.this);
            rlTermsOfUse.setOnClickListener(SettingsFragment.this);
            rlWriteUs.setOnClickListener(SettingsFragment.this);
        }
    }
}
