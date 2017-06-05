package com.example.Transportation.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Michael on 6/5/2017.
 */
@Entity
@Table(name="Accident")
public class Accident extends Event{
    private String driverLicense;
    private String driverName;
    private String otherDriverId;

    private String carType;

    @Column(name = "car_color")
    private String carColor;
    private String carNumber;

    private String insuranceCompany;


    public Accident(){}
    public Accident(Driver driver, Vehicle vehicle, String city, String street, String driverLicense, String driverName,
                    String otherDriverId, String carType, String carColor, String carNumber, String insuranceCompany)
    {
        super(driver,vehicle,city,street);
        this.driverLicense = driverLicense;
        this.driverName = driverName;
        this.otherDriverId = otherDriverId;

        this.carType = carType;
        this.carColor = carColor;
        this.carNumber = carNumber;

        this.insuranceCompany = insuranceCompany;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getOtherDriverId() {
        return otherDriverId;
    }

    public void setOtherDriverId(String otherDriverId) {
        this.otherDriverId = otherDriverId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }
}
