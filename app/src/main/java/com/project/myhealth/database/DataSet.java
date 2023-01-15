package com.project.myhealth.database;

import com.google.firebase.Timestamp;

public class DataSet {
//    Appointment
    String title, category, doctor, location, status;
    Timestamp time;
    String medicine,amount,take;

    public DataSet() {
    }

    public DataSet(String medicine, String amount, String take) {
        this.medicine = medicine;
        this.amount = amount;
        this.take = take;
    }

    public DataSet(String title, String category, String doctor, String location, Timestamp time, String status) {
        this.title = title;
        this.category = category;
        this.doctor = doctor;
        this.location = location;
        this.time = time;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTake() {
        return take;
    }

    public void setTake(String take) {
        this.take = take;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
