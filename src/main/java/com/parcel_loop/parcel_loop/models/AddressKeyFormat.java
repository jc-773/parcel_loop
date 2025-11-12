package com.parcel_loop.parcel_loop.models;

public class AddressKeyFormat {
    private String addressLine;
    private String politicalDivisionOne;
    private String politicalDivisionTwo;
    private String postCodePrimaryLow;
    private String countryCode;

    public String getAddressLine() {
        return addressLine;
    }
    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }
    public String getPoliticalDivisionOne() {
        return politicalDivisionOne;
    }
    public void setPoliticalDivisionOne(String politicalDivisionOne) {
        this.politicalDivisionOne = politicalDivisionOne;
    }
    public String getPoliticalDivisionTwo() {
        return politicalDivisionTwo;
    }
    public void setPoliticalDivisionTwo(String politicalDivisionTwo) {
        this.politicalDivisionTwo = politicalDivisionTwo;
    }
    public String getPostCodePrimaryLow() {
        return postCodePrimaryLow;
    }
    public void setPostCodePrimaryLow(String postCodePrimaryLow) {
        this.postCodePrimaryLow = postCodePrimaryLow;
    }
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
