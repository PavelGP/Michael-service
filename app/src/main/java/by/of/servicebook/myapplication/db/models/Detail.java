package by.of.servicebook.myapplication.db.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONObject;

/**
 * Created by Pavel on 14.12.2014.
 */
@Table(name = "details")
public class Detail extends Model{
    @Column(name = "detail_id")
    public int id;

    @Column(name = "job_id")
    public int job_id;

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
        this.id = jsonObject.optInt("id");
        this.job_id = jsonObject.optInt("job_id");
        this.title = jsonObject.optString("title");
        this.count = jsonObject.optInt("count");
        this.item_price = jsonObject.optLong("item_price");
        this.price = jsonObject.optLong("price");
    }
}
