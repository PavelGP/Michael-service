package by.of.servicebook.myapplication.fragments;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Formatter;
import java.util.List;

import by.of.servicebook.myapplication.MainActivity;
import by.of.servicebook.myapplication.R;
import by.of.servicebook.myapplication.models.Detail;
import by.of.servicebook.myapplication.models.Record;
import by.of.servicebook.myapplication.providers.DataProvider;
import by.of.servicebook.myapplication.utils.AppConst;

public class DetailRecordFragment extends Fragment {
    private static final String RECORD_ID = "RECORD_ID";
    private Record record;
    private DataProvider provider = DataProvider.getInstance();
    private MainActivity activity;
    Drawable dr;

    public static DetailRecordFragment newInstance(int record_id) {
        DetailRecordFragment fragment = new DetailRecordFragment();
        Bundle args = new Bundle();
        args.putInt(RECORD_ID, record_id);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailRecordFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
        ((MainActivity) activity).onSectionAttached(AppConst.FRAGMENT_DETAIL_RECORDS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail_record, container, false);


        //dr = activity.mToolbar.getNavigationIcon();
        //activity.mToolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        int record_id = 1;
        if (getArguments() != null) {
            record_id = getArguments().getInt(RECORD_ID,1);
        }
        record = provider.getRecord(record_id);

        TextView tvDate = (TextView) rootView.findViewById(R.id.date);
        TextView tvMileage = (TextView) rootView.findViewById(R.id.mileage);
        TextView tvPrice = (TextView) rootView.findViewById(R.id.price);
        TextView tvCommentDescription = (TextView) rootView.findViewById(R.id.comment_description);
        TextView tvCommentTitle = (TextView) rootView.findViewById(R.id.comment_title);
        TextView tvTotalPrice = (TextView) rootView.findViewById(R.id.total_price);
        TextView tvWorkPrice = (TextView) rootView.findViewById(R.id.work_price);
        TextView tvDetailPrice = (TextView) rootView.findViewById(R.id.detail_price);
        tvDate.setText(record.date);
        tvMileage.setText(Integer.toString(record.mileage));
        tvPrice.setText(Long.toString(record.price));
        if (record.comment.equals("")){
            tvCommentTitle.setVisibility(View.GONE);
            tvCommentDescription.setVisibility(View.GONE);
        } else {
            tvCommentDescription.setText(record.comment);
        }
        LinearLayout jobsContainer = (LinearLayout) rootView.findViewById(R.id.jobsContainer);
        int countOfJobs = record.jobList.size();
        int[] countOfDetails = new int[countOfJobs];
        long totalWorkPrice = 0;
        long totalDetailPrice = 0;
        for (int i = 0; i < countOfJobs; i++) {
            countOfDetails[i] = record.jobList.get(i).detailList.size();
            totalWorkPrice = totalWorkPrice + record.jobList.get(i).price;
            for (int j = 0; j < countOfDetails[i]; j++) {
                totalDetailPrice = totalDetailPrice + record.jobList.get(i).detailList.get(j).price;
            }
        }
        tvTotalPrice.setText(Long.toString(record.price));
        tvWorkPrice.setText(Long.toString(totalWorkPrice));
        tvDetailPrice.setText(Long.toString(totalDetailPrice));
        for (int i = 0; i < countOfJobs; i++) {
            LinearLayout jobLayout = (LinearLayout) inflater.inflate(R.layout.job_item_layout, null);
            jobsContainer.addView(jobLayout);
            TextView jobTitle = (TextView) jobLayout.findViewById(R.id.title);
            TextView jobPrice = (TextView) jobLayout.findViewById(R.id.price);
            LinearLayout detailContainer = (LinearLayout) jobLayout.findViewById(R.id.detailContainer);
            jobTitle.setText(record.jobList.get(i).title);
            jobPrice.setText(Long.toString(record.jobList.get(i).price));
            for (int j = 0; j < countOfDetails[i]; j++) {
                LinearLayout detailLayout = (LinearLayout) inflater.inflate(R.layout.detail_item_layout, null);
                detailContainer.addView(detailLayout);
                TextView detailTitle = (TextView) detailLayout.findViewById(R.id.title);
                TextView detailPrice = (TextView) detailLayout.findViewById(R.id.price);
                detailTitle.setText(record.jobList.get(i).detailList.get(j).title +
                        new Formatter()
                                .format("( %d шт.)", record.jobList.get(i).detailList.get(j).count)
                                .toString());
                detailPrice.setText(Long.toString(record.jobList.get(i).detailList.get(j).price));
            }
        }
        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new RecordsFragment())
                        .commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStop() {
        super.onStop();
        //activity.mToolbar.setNavigationIcon(dr);
    }
}
