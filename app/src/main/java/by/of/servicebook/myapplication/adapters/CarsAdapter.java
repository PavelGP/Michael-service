package by.of.servicebook.myapplication.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import by.of.servicebook.myapplication.R;
import by.of.servicebook.myapplication.db.models.Car;
import by.of.servicebook.myapplication.utils.AppConst;

/**
 * Created by Pavel on 21.04.2015.
 */
public class CarsAdapter extends ArrayAdapter<Car> {
    LayoutInflater inflater;
    final SharedPreferences preferences;

    public CarsAdapter(Context context, int resource, List<Car> objects, SharedPreferences preferences) {
        super(context, resource, objects);
        this.inflater = LayoutInflater.from(context);
        this.preferences = preferences;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_car, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Car item = getItem(position);
        String carId = preferences.getString(AppConst.VEHICLE, "");
        if (carId == item.key){
            viewHolder.chb.setChecked(true);
        } else {
            viewHolder.chb.setChecked(false);
        }
        viewHolder.tvMake.setText(item.make);
        viewHolder.tvModel.setText(item.model);
        viewHolder.tvYear.setText("" + item.year);
        viewHolder.tvRegNumber.setText(item.regNumber);

        viewHolder.chb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(AppConst.VEHICLE, item.key);
                editor.apply();
                CarsAdapter.this.notifyDataSetChanged();
            }
        });

        return convertView;
    }

    class ViewHolder {
        CheckBox chb;
        TextView tvMake, tvModel, tvYear, tvRegNumber;

        ViewHolder (View view){
            this.chb = (CheckBox) view.findViewById(R.id.chb);
            this.tvMake = (TextView) view.findViewById(R.id.tvMake);
            this.tvModel = (TextView) view.findViewById(R.id.tvModel);
            this.tvYear = (TextView) view.findViewById(R.id.tvYear);
            this.tvRegNumber = (TextView) view.findViewById(R.id.tvRegNumber);
        }
    }
}
