package com.parcel_loop.parcel_loop.data.documents;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserAuthDoc")
public class UserAuthDoc {
    private String email;
    private byte[] password;

    public UserAuthDoc(String email, byte[] password) {
        this.email = email;
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmaill(String emaill) {
        this.email = emaill;
    }
    public byte[] getHashedPassword() {
        return password;
    }
    public void setHashedPassword(byte[] password) {
        this.password = password;
    }
}
