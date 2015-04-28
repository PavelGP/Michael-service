package by.of.servicebook.myapplication.db.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONObject;

/**
 * Created by Pavel on 22.04.2015.
 */
@Table(name = "statistics")
public class Statistic extends Model {
    @Column(name = "key")
    public int key;

    @Column(name = "car_id")
    public int car_id;

    @Column(name = "mileage")
    public int mileage;

    @Column(name = "yearly_mileage")
    public int yearlyMileage;

    @Column(name = "monthly_mileage")
    public int monthlyMileage;

    @Column(name = "daily_mileage")
    public int dailyMileage;

    @Column(name = "all_exp")
    public int allExp;

    @Column(name = "yearly_exp")
    public int yearlyExp;

    @Column(name = "monthly_exp")
    public int monthlyExp;

    @Column(name = "daily_exp")
    public int dailyExp;

    @Column(name = "exp_per_100")
    public int expPer100;

    public Statistic() {
        super();
    }

    public Statistic(int key, int car_id, int mileage, int yearlyMileage, int monthlyMileage,
                     int dailyMileage, int allExp, int yearlyExp, int monthlyExp,
                     int dailyExp, int expPer100) {
        this.key = key;
        this.car_id = car_id;
        this.mileage = mileage;
        this.yearlyMileage = yearlyMileage;
        this.monthlyMileage = monthlyMileage;
        this.dailyMileage = dailyMileage;
        this.allExp = allExp;
        this.yearlyExp = yearlyExp;
        this.monthlyExp = monthlyExp;
        this.dailyExp = dailyExp;
        this.expPer100 = expPer100;
    }

    public Statistic(JSONObject jsonObject) {
        super();
        this.key = jsonObject.optInt("id");
        this.car_id = jsonObject.optInt("car_id");
        this.mileage = jsonObject.optInt("mileage");
        this.yearlyMileage = jsonObject.optInt("yearly_mileage");
        this.monthlyMileage = jsonObject.optInt("monthly_mileage");
        this.dailyMileage = jsonObject.optInt("daily_mileage");
        this.allExp = jsonObject.optInt("all_exp");
        this.yearlyExp = jsonObject.optInt("yearly_exp");
        this.monthlyExp = jsonObject.optInt("monthly_exp");
        this.dailyExp = jsonObject.optInt("daily_exp");
        this.expPer100 = jsonObject.optInt("100km_exp");
    }
}
