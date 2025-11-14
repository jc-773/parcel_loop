package com.parcel_loop.parcel_loop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parcel_loop.parcel_loop.data.documents.UserReturnsHomeDoc;
import com.parcel_loop.parcel_loop.data.repositories.UserReturnsHomeRepo;

@Service
public class DataLayerFronCronJob {
    
    UserReturnsHomeRepo userAuthRepo;

    @Autowired
    public DataLayerFronCronJob(UserReturnsHomeRepo userAuthRepo) {
        this.userAuthRepo = userAuthRepo;
    }

    public UserReturnsHomeDoc getUser(String email) {
        var result = userAuthRepo.findByEmail(email);
        if(!result.isPresent()) {
            return null;
        }
        var user = result.get();
        return user;
    }

    public void saveUserAfterInjectingReturns(UserReturnsHomeDoc user) {
        userAuthRepo.save(user);
    }
}
