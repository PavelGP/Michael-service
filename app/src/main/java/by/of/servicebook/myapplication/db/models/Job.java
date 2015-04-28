package by.of.servicebook.myapplication.db.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Pavel on 10.12.2014.
 */
@Table(name = "jobs")
public class Job extends Model {

    @Column(name = "job_id")
    public int job_id;

    @Column(name = "record_id")
    public int record_id;

    @Column(name = "title")
    public String title;

    @Column(name = "price")
    public long price;

    public List<Detail> detailList;

    public Job(){
        super();
    }

    public Job(JSONObject jsonObject) {
        super();
        this.job_id = jsonObject.optInt("job_id");
        this.record_id = jsonObject.optInt("record_id");
        this.title = jsonObject.optString("title");
        this.price = jsonObject.optLong("job_price");
    }
}
