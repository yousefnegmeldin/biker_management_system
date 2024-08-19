package com.brightskies.biker_system.Authentication.mapper;
import com.brightskies.biker_system.Authentication.dto.UserDTO;
import com.brightskies.biker_system.models.User;
import org.springframework.stereotype.Component;


public class UserMapper {
    // Converts User entity to UserDTO
    public static UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(user.getName(), user.getEmail());
    }

}
