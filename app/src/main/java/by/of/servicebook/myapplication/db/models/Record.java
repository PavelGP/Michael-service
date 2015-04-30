package by.of.servicebook.myapplication.db.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONObject;

import java.util.List;

import by.of.servicebook.myapplication.parse.models.ParseRecord;
import by.of.servicebook.myapplication.utils.AppUtils;

/**
 * Created by Pavel on 09.12.2014.
 */

@Table(name = "records")
public class Record extends Model {

    @Column(name = "key")
    public String key;

    @Column(name = "car_id")
    public String car_id;

    @Column(name = "date")
    public long date;

    @Column(name = "mileage")
    public int mileage;

    @Column(name = "service_name")
    public String serviceName;

    @Column(name = "price")
    public long price;

    @Column(name = "comment")
    public String comment;

    public List<Job> jobList;

    public Record(){
        super();
    }

    public Record(String id, String car_id, long date, int mileage, String serviceName, long price, String comment) {
        this.key = id;
        this.car_id = car_id;
        this.date = date;
        this.mileage = mileage;
        this.serviceName = serviceName;
        this.price = price;
        this.comment = comment;
    }

    public Record(JSONObject jsonObject) {
        super();
        this.key = jsonObject.optString("id");
        this.car_id = jsonObject.optString("car_id");
        this.date = jsonObject.optLong("date");
        this.mileage = jsonObject.optInt("mileage");
        this.serviceName = jsonObject.optString("service_name");
        this.price = jsonObject.optLong("price");
        this.comment = jsonObject.optString("comment");
    }

    public Record(ParseRecord parseRecord) {
        super();
        this.key = parseRecord.getObjectId();
        this.car_id = parseRecord.getString(ParseRecord.CAR_ID);
        this.date = AppUtils.parseDate(parseRecord.getString(ParseRecord.DATE));
        this.mileage = parseRecord.getInt(ParseRecord.MILEAGE);
        this.serviceName = parseRecord.getString(ParseRecord.SERVICE_NAME);
        this.price = parseRecord.getInt(ParseRecord.PRICE);
        this.comment = parseRecord.getString(ParseRecord.COMMENT);
    }
}

