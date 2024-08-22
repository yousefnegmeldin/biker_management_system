package com.brightskies.biker_system.authentication.utility;

import com.brightskies.biker_system.generalmodels.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

    public static Long getCurrentUserId() {
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return Long.parseLong(userDetails.getUsername());
        return userDetails.getId();
    }
}