package com.parcel_loop.parcel_loop.models.response_objects.usps;

import java.util.List;

public class AddressValidateResponse {
    private String firm;
    private Address address;
    private AdditionalInfo additionalInfo;
    private List<Correction> corrections;
    private List<Match> matches;
    
    public String getFirm() {
        return firm;
    }
    public void setFirm(String firm) {
        this.firm = firm;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }
    public void setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
    public List<Correction> getCorrections() {
        return corrections;
    }
    public void setCorrections(List<Correction> corrections) {
        this.corrections = corrections;
    }
    public List<Match> getMatches() {
        return matches;
    }
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    
}
