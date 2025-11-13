package com.parcel_loop.parcel_loop.controllers.authentication;

import org.springframework.web.bind.annotation.RestController;

import com.parcel_loop.parcel_loop.configuration.authentication.JwtUtility;
import com.parcel_loop.parcel_loop.data.repositories.UserAuthRepo;
import com.parcel_loop.parcel_loop.models.UserAuth;
import com.parcel_loop.parcel_loop.models.response_objects.authentication.SignUpAuthResponse;

import at.favre.lib.crypto.bcrypt.BCrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class LoginController {

    private UserAuthRepo userAuthRepo;
    private JwtUtility jwtUtility;

    @Autowired
    public LoginController(UserAuthRepo userAuthRepo, JwtUtility jwtUtility) {
        this.userAuthRepo = userAuthRepo;
        this.jwtUtility = jwtUtility;
    }
    //  * EASY FLOW
    //      * -----------
    //      * 1. optional var to store the findByEmail
    //      * 2. check var value if present
    //      *      a. if the the findByEmail result is blank or not present then we don't have the email saved. 
    //      *          this means the user must register
    //      * 3. get the value out of the optional by calling .get() and store in var
    //      * 4. call on BCrypt to compare hashedPassword from var in step 3 to the the loginRequest password to charArray()
    //      *      a. this can be done using BCrypt verifyer verify()
    //      * 5. check that BCrypt result passed. If not, return invalid credentials response
    //      * 6. if result passed, call on JwtUtility to get jwt string. Store in var
    //      * 7. Respond with jwt
    //      * 
    //      * BONUS: when user email is not found, provide register link
    //      * 
    //      * 
    //      * 

    @PostMapping("/auth/login")
    public ResponseEntity<SignUpAuthResponse> postMethodName(@RequestBody UserAuth user) {
        var requestEmail = user.getEmail();
        var result = userAuthRepo.findByEmail(requestEmail);
        if(!result.isPresent()) {
            SignUpAuthResponse response = new SignUpAuthResponse("User not found", null);
            return ResponseEntity.badRequest().body(response);
        }
        var validUser = result.get();
        String requestPassword = user.getPassword();
        var bcryptHashResult = BCrypt.verifyer().verify(requestPassword.toCharArray(), validUser.getHashedPassword());
        if(!bcryptHashResult.verified) {
            SignUpAuthResponse response = new SignUpAuthResponse("Password does not match", null);
            return ResponseEntity.badRequest().body(response);
        }

        var jwt = jwtUtility.generateToken(requestEmail);
        SignUpAuthResponse response = new SignUpAuthResponse("Successful login", jwt);
        return ResponseEntity.ok().body(response);
    }
}
