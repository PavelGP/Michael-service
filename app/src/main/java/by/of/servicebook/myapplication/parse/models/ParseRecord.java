package by.of.servicebook.myapplication.parse.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import by.of.servicebook.myapplication.db.models.Record;

/**
 * Created by Pavel on 28.04.2015.
 */
@ParseClassName("Records")
public class ParseRecord extends ParseObject {
    public static final String CAR_ID = "car_id";
    public static final String DATE = "date";
    public static final String MILEAGE = "mileage";
    public static final String PRICE = "price";
    public static final String SERVICE_NAME = "service_name";
    public static final String COMMENT = "comment";
    public static final String JOBS_RELATION = "jobs_relation";

    public ParseRecord(){}

    public ParseRecord(Record record, String carId){
        this.put(CAR_ID, carId);
        this.put(DATE, record.date);
        this.put(MILEAGE, record.mileage);
        this.put(PRICE, record.price);
        this.put(SERVICE_NAME, record.serviceName);
        this.put(COMMENT, record.comment);
    }

}