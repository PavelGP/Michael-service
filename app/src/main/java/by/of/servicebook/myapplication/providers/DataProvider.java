package by.of.servicebook.myapplication.providers;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import by.of.servicebook.myapplication.models.Detail;
import by.of.servicebook.myapplication.models.Job;
import by.of.servicebook.myapplication.models.Record;

/**
 * Created by Pavel on 11.12.2014.
 */
public class DataProvider {
    private volatile static DataProvider instance;

    public static DataProvider getInstance() {
        if (instance == null) {
            synchronized (DataProvider.class) {
                if (instance == null) {
                    instance = new DataProvider();
                }
            }
        }
        return instance;
    }

    public List<Record> getAllRecords() {
        List<Record> records = new Select().from(Record.class).execute();
        for (Record record:records){
            record.jobList = new Select().from(Job.class).where("record_id = ?", record.key).execute();
        }
        return records;
    }

    public List<Record> getRecordsByCarId(int car_id) {
        List<Record> records = new Select().from(Record.class).where("car_id = ?", car_id).execute();
        for (Record record:records){
            record.jobList = new Select().from(Job.class).where("record_id = ?", record.key).execute();
        }
        return records;
    }

    public List<Detail> getDetails(){
        return new Select().from(Detail.class).execute();
    }

    public Record getRecord(int id) {
        Record record = new Select().from(Record.class).where("key = ?", id).executeSingle();
        record.jobList = new Select().from(Job.class).where("record_id = ?", id).execute();
        for (int i = 0; i < record.jobList.size(); i++) {
            record.jobList.get(i).detailList = new Select()
                    .from(Detail.class)
                    .where("job_id = ?", record.jobList.get(i).job_id)
                    .execute();
        }
        return record;
    }
}
