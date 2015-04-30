package by.of.servicebook.myapplication.commands;

import android.os.AsyncTask;
import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.List;

import by.of.servicebook.myapplication.db.DataProvider;
import by.of.servicebook.myapplication.db.models.Car;
import by.of.servicebook.myapplication.db.models.Detail;
import by.of.servicebook.myapplication.db.models.Job;
import by.of.servicebook.myapplication.db.models.Record;
import by.of.servicebook.myapplication.parse.models.ParseCar;
import by.of.servicebook.myapplication.parse.models.ParseDetail;
import by.of.servicebook.myapplication.parse.models.ParseJob;
import by.of.servicebook.myapplication.parse.models.ParseRecord;

/**
 * Created by Pavel on 29.04.2015.
 */
public class SaveToParse extends AsyncTask<Void,Void,Void> {
    Car car;
    Callback callback;

    public SaveToParse (Car car, Callback callback){
        this.car = car;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Void... params) {
        DataProvider provider = DataProvider.getInstance();
        try {
            ParseQuery<ParseUser> userParseQuery = ParseQuery.getQuery(ParseUser.class);
            userParseQuery.whereEqualTo("username", "gulevichpavel@gmail.com");
            ParseUser user = userParseQuery.getFirst();
            ParseRelation<ParseCar> userToCarsRelation = user.getRelation("cars_relation");

            ParseQuery<ParseCar> carParseQuery = ParseQuery.getQuery(ParseCar.class);
            carParseQuery.whereEqualTo(ParseCar.VIN_CODE, car.vinCode);

            ParseCar parseCar;
            try {
                parseCar = carParseQuery.getFirst();
            } catch (ParseException e){
                parseCar = new ParseCar(car);
                parseCar.save();
            }
            userToCarsRelation.add(parseCar);
            user.save();

            ParseRelation<ParseRecord> carToRecordsRelation = parseCar.getRelation(ParseCar.RECORDS_RELATION);

            List<Record> records = provider.getRecordsByCarId(car.key);
            for (Record rec: records){
                //save record
                Record record = provider.getRecord(rec.key);
                ParseRecord parseRecord = new ParseRecord(record, parseCar.getObjectId());
                parseRecord.save();
                carToRecordsRelation.add(parseRecord);

                ParseRelation<ParseJob> recordToJobRelation = parseRecord.getRelation(ParseRecord.JOBS_RELATION);
                List<Job> jobList = record.jobList;
                for(Job job:jobList){
                    //save job
                    ParseJob parseJob = new ParseJob(job, parseRecord.getObjectId());
                    parseJob.save();
                    recordToJobRelation.add(parseJob);

                    ParseRelation<ParseDetail> jobToDetailRelation = parseRecord.getRelation(ParseJob.DETAILS_RELATION);
                    List<Detail> detailList = job.detailList;
                    for (Detail detail:detailList){
                        //save detail
                        ParseDetail parseDetail = new ParseDetail(detail, parseJob.getObjectId());
                        parseDetail.save();
                        jobToDetailRelation.add(parseDetail);
                    }
                    parseJob.save();
                }
                parseRecord.save();
            }
            parseCar.save();

        }catch ( ParseException e){
            Log.d(getClass().getName(), "ParseException");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        callback.doCallback();
    }

    public interface Callback{
        void doCallback();
    }
}
