package com.parcel_loop.parcel_loop.controllers.experience;

import org.springframework.web.bind.annotation.RestController;

import com.parcel_loop.parcel_loop.configuration.authentication.JwtUtility;
import com.parcel_loop.parcel_loop.data.repositories.UserReturnsHomeRepo;
import com.parcel_loop.parcel_loop.models.response_objects.experience.Returns;
import com.parcel_loop.parcel_loop.models.response_objects.experience.UserReturnsHomeResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UserReturnsHomeController {
    
    private JwtUtility jwtUtility;
    private UserReturnsHomeRepo userReturnsRepo;
    
    @Autowired
    public UserReturnsHomeController(JwtUtility jwtUtility, UserReturnsHomeRepo userReturnsRepo) {
        this.jwtUtility = jwtUtility;
        this.userReturnsRepo = userReturnsRepo;
    }

    @GetMapping("/returns")
    public ResponseEntity<UserReturnsHomeResponse> getUserReturns(@RequestParam String email, @RequestHeader String authorization) {
        var subject = jwtUtility.validateAndGetSubject(authorization);
        if(subject == null ||subject.isEmpty() || subject.isBlank()) {
            UserReturnsHomeResponse returnsHomeResponse = new UserReturnsHomeResponse();
            returnsHomeResponse.setMessage("Failed to validate jwt token. Check that the jwt token you provided in the" + 
            "'Authorization' header is correct." );//Maybe an enum to map to something???
            return ResponseEntity.status(405).body(returnsHomeResponse);
        }
        var result = userReturnsRepo.findByEmail(email);
        if(!result.isPresent()) {
            UserReturnsHomeResponse returnsHomeResponse = new UserReturnsHomeResponse();
            returnsHomeResponse.setMessage("User not found");//Maybe an enum to map to something???
            return ResponseEntity.status(404).body(returnsHomeResponse);
        } 
        UserReturnsHomeResponse returnsHomeResponse = new UserReturnsHomeResponse();
        var user = result.get();
        returnsHomeResponse.setReturns(user.getReturns()); 
        returnsHomeResponse.setMessage(null);
        return ResponseEntity.ok().body(returnsHomeResponse);
    }
    
}
