package com.parcel_loop.parcel_loop.configuration.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.parcel_loop.parcel_loop.data.repositories.UserAuthRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserAuthRepo userRepo;
    private AuthenticationManager authenticationManager;

    @Autowired
    public CustomUserDetailsService(UserAuthRepo userRepo, @Lazy AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var userOpt = userRepo.findByEmail(email);
        var user = userOpt.get();
        Authentication authInputToken = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getHashedPassword());
        var authUser = authenticationManager.authenticate(authInputToken);
        if(!authUser.isAuthenticated()) {
            return null;
        }
       return (UserDetails) authInputToken.getPrincipal();
    }
}
