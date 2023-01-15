package com.project.myhealth.database;

import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Profile {
    private String  fullName, userID, address, city;
    private Timestamp dateOfBirth;
    private String phoneNumber, email;

    public Profile() {

    }

    public Profile(String fullName, String userID, String address, String city, Timestamp dateOfBirth, String phoneNumber, String email) {
        this.fullName = fullName;
        this.userID = userID;
        this.address = address;
        this.city = city;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserID() {
        return userID;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getDateOfBirth() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy", Locale.getDefault());
        return dateFormat.format(dateOfBirth.toDate()).toString();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
