package com.shopopedia.user.service;

import com.shopopedia.user.dto.RegisterUserRequest;
import com.shopopedia.user.dto.UserResponse;

public interface UserService {

    UserResponse registerUser(RegisterUserRequest request);

    UserResponse getUserById(Long id);

    UserResponse getUserByEmail(String email);
}
