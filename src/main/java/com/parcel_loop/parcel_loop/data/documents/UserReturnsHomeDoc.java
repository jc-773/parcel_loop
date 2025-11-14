package com.parcel_loop.parcel_loop.data.documents;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.parcel_loop.parcel_loop.models.response_objects.experience.Returns;

@Document(collection = "UserReturnsHomeDoc")
public class UserReturnsHomeDoc {
    private String email;
    private List<Returns> returns;

    public UserReturnsHomeDoc(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<Returns> getReturns() {
        return returns;
    }
    public void setReturns(List<Returns> returns) {
        this.returns = returns;
    }
}
