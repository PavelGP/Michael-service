package by.of.servicebook.myapplication.db.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONObject;

import by.of.servicebook.myapplication.parse.models.ParseDetail;

/**
 * Created by Pavel on 14.12.2014.
 */
@Table(name = "details")
public class Detail extends Model{
    @Column(name = "key")
    public String id;

    @Column(name = "job_id")
    public String job_id;

    @Column(name = "title")
    public String title;

    @Column(name = "count")
    public int count;

    @Column(name = "item_price")
    public long item_price;

    @Column(name = "price")
    public long price;

    public Detail(){
        super();
    }

    public Detail(JSONObject jsonObject) {
        super();
        this.id = jsonObject.optString("id");
        this.job_id = jsonObject.optString("job_id");
        this.title = jsonObject.optString("title");
        this.count = jsonObject.optInt("count");
        this.item_price = jsonObject.optLong("item_price");
        this.price = jsonObject.optLong("price");
    }

    public Detail(ParseDetail parseDetail) {
        super();
        this.id = parseDetail.getObjectId();
        this.job_id = parseDetail.getString(ParseDetail.JOB_ID);
        this.title = parseDetail.getString(ParseDetail.TITLE);
        this.count = parseDetail.getInt(ParseDetail.COUNT);
        this.item_price = parseDetail.getLong(ParseDetail.ITEM_PRICE);
        this.price = parseDetail.getLong(ParseDetail.PRICE);
    }
}
