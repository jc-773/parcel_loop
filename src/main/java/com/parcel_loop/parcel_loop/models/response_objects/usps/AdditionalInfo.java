package com.parcel_loop.parcel_loop.models.response_objects.usps;

public class AdditionalInfo {
    private String deliveryPoint;
    private String carrierRoute;
    private String DPVConfirmation;
    private String DPVCMRA;
    private String business;
    private String centralDeliveryPoint;
    private String vacant;
    
    public String getDeliveryPoint() {
        return deliveryPoint;
    }
    public void setDeliveryPoint(String deliveryPoint) {
        this.deliveryPoint = deliveryPoint;
    }
    public String getCarrierRoute() {
        return carrierRoute;
    }
    public void setCarrierRoute(String carrierRoute) {
        this.carrierRoute = carrierRoute;
    }
    public String getDPVConfirmation() {
        return DPVConfirmation;
    }
    public void setDPVConfirmation(String dPVConfirmation) {
        DPVConfirmation = dPVConfirmation;
    }
    public String getDPVCMRA() {
        return DPVCMRA;
    }
    public void setDPVCMRA(String dPVCMRA) {
        DPVCMRA = dPVCMRA;
    }
    public String getBusiness() {
        return business;
    }
    public void setBusiness(String business) {
        this.business = business;
    }
    public String getCentralDeliveryPoint() {
        return centralDeliveryPoint;
    }
    public void setCentralDeliveryPoint(String centralDeliveryPoint) {
        this.centralDeliveryPoint = centralDeliveryPoint;
    }
    public String getVacant() {
        return vacant;
    }
    public void setVacant(String vacant) {
        this.vacant = vacant;
    }

    
}
