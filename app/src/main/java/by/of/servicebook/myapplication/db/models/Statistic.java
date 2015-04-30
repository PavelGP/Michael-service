package by.of.servicebook.myapplication.db.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONObject;

import by.of.servicebook.myapplication.parse.models.ParseStatistic;

/**
 * Created by Pavel on 22.04.2015.
 */
@Table(name = "statistics")
public class Statistic extends Model {
    @Column(name = "key")
    public String key;

    @Column(name = "car_id")
    public String car_id;

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

    public Statistic(String key, String car_id, int mileage, int yearlyMileage, int monthlyMileage,
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
        this.key = jsonObject.optString("id");
        this.car_id = jsonObject.optString("car_id");
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

    public Statistic(ParseStatistic parseStatistic) {
        super();
        this.key = parseStatistic.getObjectId();
        this.car_id = parseStatistic.getString(ParseStatistic.CAR_ID);
        this.mileage = parseStatistic.getInt(ParseStatistic.MILEAGE);
        this.yearlyMileage = parseStatistic.getInt(ParseStatistic.YEARLY_MILEAGE);
        this.monthlyMileage = parseStatistic.getInt(ParseStatistic.MONTHLY_MILEAGE);
        this.dailyMileage = parseStatistic.getInt(ParseStatistic.DAILY_MILEAGE);
        this.allExp = parseStatistic.getInt(ParseStatistic.ALL_EXP);
        this.yearlyExp = parseStatistic.getInt(ParseStatistic.YEARLY_EXP);
        this.monthlyExp = parseStatistic.getInt(ParseStatistic.MONTHLY_EXP);
        this.dailyExp = parseStatistic.getInt(ParseStatistic.DAILY_MILEAGE);
        this.expPer100 = parseStatistic.getInt(ParseStatistic.EXP_PER_100KM);
    }
}
