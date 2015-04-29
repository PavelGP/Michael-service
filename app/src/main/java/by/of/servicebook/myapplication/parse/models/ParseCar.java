package by.of.servicebook.myapplication.parse.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import by.of.servicebook.myapplication.db.models.Car;

/**
 * Created by Pavel on 28.04.2015.
 */
@ParseClassName("Cars")
public class ParseCar extends ParseObject {
    public static final String MAKE = "make";
    public static final String MODEL = "model";
    public static final String YEAR = "year";
    public static final String REG_NUMBER = "reg_number";
    public static final String VIN_CODE = "vin_code";
    public static final String COLOR = "color";
    public static final String REG_DATE = "reg_date";
    public static final String REG_MILEAGE = "reg_mileage";
    public static final String RECORDS_RELATION = "records_relation";

    public ParseCar(){}

    public ParseCar (Car car){
        this.put(MAKE, car.make);
        this.put(MODEL, car.model);
        this.put(YEAR, car.year);
        this.put(REG_NUMBER, car.regNumber);
        this.put(VIN_CODE, car.vinCode);
        this.put(COLOR, car.color);
        this.put(REG_DATE, car.regDate);
        this.put(REG_MILEAGE, car.regMileage);
    }

}
