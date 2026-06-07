package com.shopopedia.user.dto;

import com.shopopedia.user.entity.UserStatus;

public record UserResponse(
        Long id,
        String email,
        String firstName,
        String contact,
        String lastName,
        UserStatus status
) {
}
