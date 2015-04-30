package by.of.servicebook.myapplication.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import by.of.servicebook.myapplication.R;
import by.of.servicebook.myapplication.db.models.Record;
import by.of.servicebook.myapplication.utils.AppUtils;

/**
 * Created by Pavel on 11.12.2014.
 */
public class RecordsAdapter extends ArrayAdapter {
    private Context activity;

    public RecordsAdapter(Activity activity, List<Record> records) {
        super(activity, R.layout.record_item, records);
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        Record record = (Record) getItem(position);
        int jobCount = record.jobList.size();

        LayoutInflater mInflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.record_item, null);
            viewHolder = new ViewHolder();
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.mileage = (TextView) convertView.findViewById(R.id.mileage);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price);
            viewHolder.comment_title = (TextView) convertView.findViewById(R.id.comment_title);
            viewHolder.comment_description = (TextView) convertView.findViewById(R.id.comment_description);
            viewHolder.tvContainer = (LinearLayout) convertView.findViewById(R.id.tvConteiner);
            viewHolder.jobsTvList = new ArrayList<TextView>();
            for (int i=0; i<jobCount; i++){
                LinearLayout ll = (LinearLayout) mInflater.inflate(R.layout.record_textview, null);
                TextView tv = (TextView) ll.findViewById(R.id.record);
                viewHolder.tvContainer.addView(ll, i);
                viewHolder.jobsTvList.add(tv);
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.date.setText(AppUtils.dateToString(record.date));
        viewHolder.price.setText(String.format("%,d", record.price));
        viewHolder.mileage.setText(String.format("%,d", record.mileage));
        int jobContainerCount = viewHolder.tvContainer.getChildCount();
        if (jobContainerCount > jobCount) {
            viewHolder.tvContainer.removeViews(jobCount, jobContainerCount - jobCount);
            for (int i = jobCount; i < jobContainerCount; i++) {
                viewHolder.jobsTvList.remove(jobCount);
            }
        } else if (jobContainerCount < jobCount) {
            for (int i=jobContainerCount; i<jobCount; i++){
                LinearLayout ll = (LinearLayout) mInflater.inflate(R.layout.record_textview, null);
                TextView tv = (TextView) ll.findViewById(R.id.record);
                viewHolder.tvContainer.addView(ll, i);
                viewHolder.jobsTvList.add(tv);
            }
        }
        for (int i=0; i<jobCount; i++){
            viewHolder.jobsTvList.get(i).setText(record.jobList.get(i).title);
        }
        if (!record.comment.contentEquals("")){
            viewHolder.comment_title.setVisibility(View.VISIBLE);
            viewHolder.comment_description.setVisibility(View.VISIBLE);
            viewHolder.comment_description.setText(record.comment);
        } else {
            viewHolder.comment_title.setVisibility(View.GONE);
            viewHolder.comment_description.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder {
        TextView date, mileage, price, comment_title, comment_description;
        LinearLayout tvContainer;
        List<TextView> jobsTvList;
    }
}
