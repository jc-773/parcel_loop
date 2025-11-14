package com.parcel_loop.parcel_loop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.parcel_loop.parcel_loop.data.documents.UserReturnsHomeDoc;
import com.parcel_loop.parcel_loop.models.response_objects.experience.Returns;

@Component
public class CronJobMockEnv {


    DataLayerFronCronJob dataLayerFronCronJob;

    @Autowired
    public CronJobMockEnv(DataLayerFronCronJob dataLayerFronCronJob) {
        this.dataLayerFronCronJob = dataLayerFronCronJob;
    }

    //@Scheduled(cron = "*/10 * * * * *")
    public void injectUserWithReturns() {
        UserReturnsHomeDoc user = new UserReturnsHomeDoc("testemail_1");
        List<Returns> listOfReturns = new ArrayList<>();
        generateReturns(10, listOfReturns);
        user.setReturns(listOfReturns);
        dataLayerFronCronJob.saveUserAfterInjectingReturns(user);
        // var foundUser = dataLayerFronCronJob.getUser(user.getEmail());
        // if(foundUser != null) {

        // }
    }

    private void generateReturns(int n, List<Returns> listOfReturns) {
        for(int i = 0; i < n; i++) {
            Returns returns = new Returns();
            if(i % n == 0) {
                returns.setCarrier("UPS");
            } else {
                returns.setCarrier("FedEx");
            }
            returns.setLabelUrl("url");
            returns.setEmailId("email" + i);
            listOfReturns.add(returns);
        }
    }
}
