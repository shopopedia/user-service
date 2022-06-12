package com.shopopedia.userservice.mapper;

import com.shopopedia.userservice.domain.User;
import com.shopopedia.userservice.model.UserRequest;
import com.shopopedia.userservice.model.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    //domain to model(response)
    public UserResponse userResponseMapper(User user) {
        UserResponse userResponse = new UserResponse();

        userResponse.setUserId(user.getUserId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmailId(user.getEmailId());
        userResponse.setPassword(user.getPassword());
        userResponse.setMobileNo(user.getMobileNo());
        userResponse.setRoleType(user.getRoleType());
        return userResponse;
    }

    //model(request) to domain
    public User userRequestMapper(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmailId(userRequest.getEmailId());
        user.setPassword(userRequest.getPassword());
        user.setMobileNo(userRequest.getMobileNo());
        user.setRoleType(userRequest.getRoleType());
        return user;
    }
}
