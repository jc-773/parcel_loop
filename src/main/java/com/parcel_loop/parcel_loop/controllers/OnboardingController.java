package com.parcel_loop.parcel_loop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.parcel_loop.parcel_loop.models.ShipperProfile;
import com.parcel_loop.parcel_loop.services.authentication.OnboardingSerevice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class OnboardingController {
    
    private OnboardingSerevice onboardingService;

    @Autowired
    public OnboardingController(OnboardingSerevice onboardingSerevice) {
        this.onboardingService = onboardingSerevice;
    }

    @PostMapping("/auth/onboarding")
    public String postUserOnboarding(@RequestBody ShipperProfile shipperProfile) {
        //pass to service for validation, db stuff, etc        
        return null;
    }
    
}
