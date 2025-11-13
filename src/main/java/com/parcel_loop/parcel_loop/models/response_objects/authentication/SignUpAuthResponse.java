package com.parcel_loop.parcel_loop.models.response_objects.authentication;

public class SignUpAuthResponse {
    private String message;
    private String jwt;

    public SignUpAuthResponse(String message, String jwt) {
        this.message = message;
        this.jwt = jwt;
    }
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getJwt() {
        return jwt;
    }
    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    
}
