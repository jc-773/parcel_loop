package com.parcel_loop.parcel_loop.models.response_objects.experience;

import java.util.List;

public class UserReturnsHomeResponse {
    private List<Returns> returns;
    private String message;

    public List<Returns> getReturns() {
        return returns;
    }

    public void setReturns(List<Returns> returns) {
        this.returns = returns;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
