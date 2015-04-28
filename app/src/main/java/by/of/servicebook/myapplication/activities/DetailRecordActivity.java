package by.of.servicebook.myapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Formatter;

import by.of.servicebook.myapplication.R;
import by.of.servicebook.myapplication.db.models.Record;
import by.of.servicebook.myapplication.db.DataProvider;

public class DetailRecordActivity extends ActionBarActivity {
    private final int DEFAUL_RECORD_ID = 1;
    private static final String RECORD_ID = "record_id";
    private Record record;

    public static void launch(Context context, int recordId){
        Intent intent = new Intent(context, DetailRecordActivity.class);
        intent.putExtra(RECORD_ID, recordId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_record);

        setToolbar();
        initData();
        initViews();
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(this);

        TextView tvServiceName = (TextView) findViewById(R.id.tvServiceName);
        TextView tvDate = (TextView) findViewById(R.id.date);
        TextView tvMileage = (TextView) findViewById(R.id.mileage);
        TextView tvPrice = (TextView) findViewById(R.id.price);
        TextView tvCommentDescription = (TextView) findViewById(R.id.comment_description);
        TextView tvCommentTitle = (TextView) findViewById(R.id.comment_title);
        TextView tvTotalPrice = (TextView) findViewById(R.id.total_price);
        TextView tvWorkPrice = (TextView) findViewById(R.id.work_price);
        TextView tvDetailPrice = (TextView) findViewById(R.id.detail_price);

        tvServiceName.setText(record.serviceName);
        tvDate.setText(record.date);
        tvMileage.setText(Integer.toString(record.mileage));
        tvPrice.setText(Long.toString(record.price));
        if (record.comment.equals("")){
            tvCommentTitle.setVisibility(View.GONE);
            tvCommentDescription.setVisibility(View.GONE);
        } else {
            tvCommentDescription.setText(record.comment);
        }
        LinearLayout jobsContainer = (LinearLayout) findViewById(R.id.jobsContainer);
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
    }

    private void initData(){
        int recordId = DEFAUL_RECORD_ID;
        if (getIntent() != null) {
            recordId = getIntent().getExtras().getInt(RECORD_ID, 1);
        }

        record = DataProvider.getInstance().getRecord(recordId);
    }

    private void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
