package by.of.servicebook.myapplication.db.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONObject;

import java.util.List;

import by.of.servicebook.myapplication.parse.models.ParseJob;

/**
 * Created by Pavel on 10.12.2014.
 */
@Table(name = "jobs")
public class Job extends Model {

    @Column(name = "key")
    public String id;

    @Column(name = "record_id")
    public String record_id;

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
        this.id = jsonObject.optString("id");
        this.record_id = jsonObject.optString("record_id");
        this.title = jsonObject.optString("title");
        this.price = jsonObject.optLong("job_price");
    }

    public Job(ParseJob parseJob) {
        super();
        this.id = parseJob.getObjectId();
        this.record_id = parseJob.getString(ParseJob.RECORD_ID);
        this.title = parseJob.getString(ParseJob.TITLE);
        this.price = parseJob.getLong(ParseJob.PRICE);
    }
}
