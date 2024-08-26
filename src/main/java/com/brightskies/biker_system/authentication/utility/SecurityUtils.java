package com.brightskies.biker_system.authentication.utility;

import com.brightskies.biker_system.generalmodels.User;
import com.brightskies.biker_system.generalrepositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    UserRepository userRepository;
    @Autowired
    public SecurityUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public static Long getCurrentUserId() {
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails == null){
            throw new RuntimeException("User not found");
        }
//        return Long.parseLong(userDetails.getUsername());
        return userDetails.getId();
    }
}