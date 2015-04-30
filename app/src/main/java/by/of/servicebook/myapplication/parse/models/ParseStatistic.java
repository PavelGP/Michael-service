package by.of.servicebook.myapplication.parse.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import by.of.servicebook.myapplication.db.models.Statistic;

/**
 * Created by Pavel on 28.04.2015.
 */
@ParseClassName("Statistics")
public class ParseStatistic extends ParseObject {
    public static final String CAR_ID = "car_id";
    public static final String MILEAGE = "mileage";
    public static final String YEARLY_MILEAGE = "yearly_mileage";
    public static final String MONTHLY_MILEAGE = "monthly_mileage";
    public static final String DAILY_MILEAGE = "daily_mileage";
    public static final String ALL_EXP = "all_exp";
    public static final String YEARLY_EXP = "yearly_exp";
    public static final String MONTHLY_EXP = "monthly_exp";
    public static final String DAILY_EXP = "daily_exp";
    public static final String EXP_PER_100KM = "100km_exp";

    public ParseStatistic(){}

}