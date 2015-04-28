package by.of.servicebook.myapplication.db;

import com.activeandroid.query.Select;

import java.util.List;

import by.of.servicebook.myapplication.db.models.Car;
import by.of.servicebook.myapplication.db.models.Detail;
import by.of.servicebook.myapplication.db.models.Job;
import by.of.servicebook.myapplication.db.models.Record;
import by.of.servicebook.myapplication.db.models.Statistic;

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

    public Statistic getCarStatisticById(int carId){
        return new Select().from(Statistic.class).where("key = ?", carId).executeSingle();
    }

    public List<Record> getAllRecords() {
        List<Record> records = new Select().from(Record.class).execute();
        for (Record record:records){
            record.jobList = new Select().from(Job.class).where("record_id = ?", record.key).execute();
        }
        return records;
    }

    public List<Car> getAllCars(){
        return new Select().from(Car.class).execute();
    }

    public Car getCarById(int id){
        return new Select().from(Car.class).where("key = ?", id).executeSingle();
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
