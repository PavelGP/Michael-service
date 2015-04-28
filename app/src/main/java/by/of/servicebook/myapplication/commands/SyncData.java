package by.of.servicebook.myapplication.commands;

import android.content.Context;
import android.os.AsyncTask;

import com.activeandroid.query.Delete;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import by.of.servicebook.myapplication.db.models.Car;
import by.of.servicebook.myapplication.db.models.Detail;
import by.of.servicebook.myapplication.db.models.Job;
import by.of.servicebook.myapplication.db.models.Record;
import by.of.servicebook.myapplication.db.models.Statistic;

/**
 * Created by Pavel on 27.04.2015.
 */
public class SyncData extends AsyncTask<Void, Void, Void> {
    Callback callback;
    Context context;

    public SyncData(Context context, Callback callback){
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Void[] params) {
        downloadCars();
        downloadRecords();
        downloadStatistics();
        downloadJobs();
        downloadDetails();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        callback.doCallback();
    }

    private void downloadCars(){
        try {
            new Delete().from(Car.class).execute();
            InputStream inputStream = context.getAssets().open("cars.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String cars = new String(buffer);
            JSONArray jsonArray = new JSONArray(cars);
            for (int i = 0; i < jsonArray.length(); i++) {
                new Car((JSONObject) jsonArray.get(i)).save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void downloadRecords(){
        try {
            new Delete().from(Record.class).execute();
            InputStream inputStream = context.getAssets().open("records.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String records = new String(buffer);
            JSONArray jsonArray = new JSONArray(records);
            for (int i = 0; i < jsonArray.length(); i++) {
                new Record((JSONObject) jsonArray.get(i)).save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void downloadStatistics(){
        try {
            new Delete().from(Statistic.class).execute();
            InputStream inputStream = context.getAssets().open("statistics.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String statistics = new String(buffer);
            JSONArray jsonArray = new JSONArray(statistics);
            for (int i = 0; i < jsonArray.length(); i++) {
                new Statistic((JSONObject) jsonArray.get(i)).save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void downloadJobs(){
        try {
            new Delete().from(Job.class).execute();
            InputStream inputStream = context.getAssets().open("jobs.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String jobs = new String(buffer);
            JSONArray jsonArray = new JSONArray(jobs);
            for (int i = 0; i < jsonArray.length(); i++) {
                new Job((JSONObject) jsonArray.get(i)).save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void downloadDetails(){
        try {
            new Delete().from(Detail.class).execute();
            InputStream inputStream = context.getAssets().open("details.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String details = new String(buffer);
            JSONArray jsonArray = new JSONArray(details);
            for (int i = 0; i < jsonArray.length(); i++) {
                new Detail((JSONObject) jsonArray.get(i)).save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public interface Callback {
        void doCallback();
    }
}
