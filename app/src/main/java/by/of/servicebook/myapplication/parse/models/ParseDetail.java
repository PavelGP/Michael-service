package by.of.servicebook.myapplication.parse.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import by.of.servicebook.myapplication.db.models.Detail;

/**
 * Created by Pavel on 28.04.2015.
 */
@ParseClassName("Details")
public class ParseDetail extends ParseObject {
    public static final String JOB_ID = "job_id";
    public static final String TITLE = "title";
    public static final String COUNT = "count";
    public static final String ITEM_PRICE = "item_price";
    public static final String PRICE = "price";

    public ParseDetail(){}

    public ParseDetail(Detail detail, String jobId){
        put(JOB_ID, jobId);
        put(TITLE, detail.title);
        put(COUNT, detail.count);
        put(ITEM_PRICE, detail.item_price);
        put(PRICE, detail.price);
    }
}