package com.parcel_loop.parcel_loop.models.response_objects.experience;

public class Returns {
    private String emailId;
    private String merchant;
    private String carrier;
    private String deadline;
    private String labelUrl;
    private String returnId;
    
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public String getMerchant() {
        return merchant;
    }
    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }
    public String getCarrier() {
        return carrier;
    }
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
    public String getDeadline() {
        return deadline;
    }
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    public String getLabelUrl() {
        return labelUrl;
    }
    public void setLabelUrl(String labelUrl) {
        this.labelUrl = labelUrl;
    }
    public String getReturnId() {
        return returnId;
    }
    public void setReturnId(String returnId) {
        this.returnId = returnId;
    }

    
}
