package com.parcel_loop.parcel_loop.models.response_objects.usps;

public class Address {
    private String streetAddress;
    private String streetAddressAbbreviation;
    private String secondaryAddress;
    private String cityAbbreviation;
    private String city;
    private String state;
    private String ZIPCode;
    private String ZIPPlus4;
    private String urbanization;
    
    public String getStreetAddress() {
        return streetAddress;
    }
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    public String getStreetAddressAbbreviation() {
        return streetAddressAbbreviation;
    }
    public void setStreetAddressAbbreviation(String streetAddressAbbreviation) {
        this.streetAddressAbbreviation = streetAddressAbbreviation;
    }
    public String getSecondaryAddress() {
        return secondaryAddress;
    }
    public void setSecondaryAddress(String secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
    }
    public String getCityAbbreviation() {
        return cityAbbreviation;
    }
    public void setCityAbbreviation(String cityAbbreviation) {
        this.cityAbbreviation = cityAbbreviation;
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
    public String getZIPCode() {
        return ZIPCode;
    }
    public void setZIPCode(String zIPCode) {
        ZIPCode = zIPCode;
    }
    public String getZIPPlus4() {
        return ZIPPlus4;
    }
    public void setZIPPlus4(String zIPPlus4) {
        ZIPPlus4 = zIPPlus4;
    }
    public String getUrbanization() {
        return urbanization;
    }
    public void setUrbanization(String urbanization) {
        this.urbanization = urbanization;
    }

    
}
