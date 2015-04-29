package by.of.servicebook.myapplication.parse.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import by.of.servicebook.myapplication.db.models.Job;

/**
 * Created by Pavel on 28.04.2015.
 */
@ParseClassName("Jobs")
public class ParseJob extends ParseObject {
    public static final String RECORD_ID = "record_id";
    public static final String TITLE = "title";
    public static final String PRICE = "price";
    public static final String DETAILS_RELATION = "details_relation";

    public ParseJob(){}

    public ParseJob(Job job, String recordId){
        put(TITLE, job.title);
        put(PRICE, job.price);
        put(RECORD_ID, recordId);
    }
}