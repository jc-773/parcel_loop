package com.parcel_loop.parcel_loop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*  Full Name:
 * Phone Number:
 * Email (optional):
 * Street Address:
 * Apt/Suite (optional):
 * City:
 * State:
 * Zip:
 * Country:
 * Residential or Business: [select]
 * Preferred Pickup Spot (optional):*/
public class ShipperProfile {

    private String fullName;
    private String email;
    private String streetAddressOne;
    private String streetAddressTwo;
    @JsonIgnore
    private String buildingName;
    private String city;
    private String state;
    private String zip;
    private String country;
    private boolean residential;
    @JsonIgnore
    private String pickupSpot;

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getStreetAddressOne() {
        return streetAddressOne;
    }
    public void setStreetAddressOne(String streetAddressOne) {
        this.streetAddressOne = streetAddressOne;
    }
    public String getStreetAddressTwo() {
        return streetAddressTwo;
    }
    public void setStreetAddressTwo(String streetAddressTwo) {
        this.streetAddressTwo = streetAddressTwo;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public boolean isResidential() {
        return residential;
    }
    public void setResidential(boolean residential) {
        this.residential = residential;
    }
    public String getPickupSpot() {
        return pickupSpot;
    }
    public void setPickupSpot(String pickupSpot) {
        this.pickupSpot = pickupSpot;
    }
    public String getBuildingName() {
        return buildingName;
    }
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
}
