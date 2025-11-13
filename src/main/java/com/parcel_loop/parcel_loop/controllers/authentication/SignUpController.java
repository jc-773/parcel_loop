package com.parcel_loop.parcel_loop.controllers.authentication;

import org.springframework.web.bind.annotation.RestController;

import com.parcel_loop.parcel_loop.configuration.authentication.JwtUtility;
import com.parcel_loop.parcel_loop.data.documents.UserAuthDoc;
import com.parcel_loop.parcel_loop.data.repositories.UserAuthRepo;
import com.parcel_loop.parcel_loop.models.UserAuth;
import com.parcel_loop.parcel_loop.models.response_objects.authentication.SignUpAuthResponse;

import at.favre.lib.crypto.bcrypt.BCrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
 * 
 * Issuing a JWT immediately after successful signup is a good practice cause:
	•	User convenience: Automatically logs them in
	•	Consistent flow: Mirrors login behavior
	•	Stateless auth: JWT carries identity; no session storage needed 
 */
@RestController
public class SignUpController {
    
    private UserAuthRepo userAuthRepo;
    private JwtUtility jwtUtility;

    @Autowired
    public SignUpController(UserAuthRepo userAuthRepo, JwtUtility jwtUtility) {
        this.userAuthRepo = userAuthRepo;
        this.jwtUtility = jwtUtility;
    }
    //TODO: Document how Postman was returning a 403 because the filterchain method in your securityConfig class didn't whitelist the /auth/signup path. And explain why!
    @PostMapping("/auth/signup")
    public ResponseEntity<SignUpAuthResponse> userSignUp(@RequestBody UserAuth user) {
        String requestEmail = user.getEmail();
        var result = userAuthRepo.findByEmail(requestEmail);
        if(result.isPresent()) {
            var response = new SignUpAuthResponse("User already exists", null);
            return ResponseEntity.badRequest().body(response);
        } 

        String requestPassword = user.getPassword();
        var hashedPassword = BCrypt.withDefaults().hash(12, requestPassword.toCharArray());
        UserAuthDoc userAuth = new UserAuthDoc(requestEmail, hashedPassword);
        userAuthRepo.save(userAuth);
        var jwt = jwtUtility.generateToken(requestEmail);
        var response = new SignUpAuthResponse("Successfully Registered", jwt);
        return ResponseEntity.ok().body(response);
    }
    
}
