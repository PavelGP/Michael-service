package by.of.servicebook.myapplication.db.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONObject;

/**
 * Created by Pavel on 21.04.2015.
 */
@Table(name = "cars")
public class Car  extends Model {

    @Column(name = "key")
    public int key;

    @Column(name = "make")
    public String make;

    @Column(name = "model")
    public String model;

    @Column(name = "year")
    public int year;

    @Column(name = "reg_number")
    public String regNumber;

    @Column(name = "vin_code")
    public String vinCode;

    @Column(name = "color")
    public String color;

    @Column(name = "reg_date")
    public String regDate;

    @Column(name = "reg_mileage")
    public int regMileage;

    public Car(){
        super();
    }

    public Car(JSONObject jsonObject){
        this.key = jsonObject.optInt("id");
        this.make = jsonObject.optString("make");
        this.model = jsonObject.optString("model");
        this.year = jsonObject.optInt("year");
        this.regNumber = jsonObject.optString("reg_number");
        this.vinCode = jsonObject.optString("VIN_code");
        this.color = jsonObject.optString("color");
        this.regDate = jsonObject.optString("reg_date");
        this.regMileage = jsonObject.optInt("reg_mileage");
    }
}
