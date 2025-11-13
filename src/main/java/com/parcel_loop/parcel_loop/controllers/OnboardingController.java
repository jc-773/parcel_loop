package com.parcel_loop.parcel_loop.controllers;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.parcel_loop.parcel_loop.models.ShipperProfile;
import com.parcel_loop.parcel_loop.models.response_objects.usps.AddressValidateResponse;
import com.parcel_loop.parcel_loop.services.authentication.OnboardingSerevice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
public class OnboardingController {
    
    private OnboardingSerevice onboardingService;

    @Autowired
    public OnboardingController(OnboardingSerevice onboardingSerevice) {
        this.onboardingService = onboardingSerevice;
    }

    @PostMapping("/auth/onboarding")
    public ResponseEntity<String> postUserOnboarding(@RequestBody ShipperProfile shipperProfile, @RequestHeader String authorization) {
        var jwtToken = authorization;
        var addressResponse = onboardingService.isAddressValid(shipperProfile, jwtToken);
        if(addressResponse == null) return ResponseEntity.badRequest().body("No response returned for address");
        else if(addressResponse.getCorrections().size() > 0) {
            String correctionText = addressResponse.getCorrections().get(0).getText();
            return ResponseEntity.badRequest().body(correctionText);
        }
        return ResponseEntity.ok().body("Valid address");
    }
    
}
