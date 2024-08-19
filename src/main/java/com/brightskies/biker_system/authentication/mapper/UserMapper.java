package com.brightskies.biker_system.authentication.mapper;
import com.brightskies.biker_system.authentication.dto.UserDTO;
import com.brightskies.biker_system.models.User;


public class UserMapper {
    // Converts User entity to UserDTO
    public static UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(user.getName(), user.getEmail());
    }

}
