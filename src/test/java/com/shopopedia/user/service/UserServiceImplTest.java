package com.shopopedia.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.shopopedia.user.dto.RegisterUserRequest;
import com.shopopedia.user.dto.UserResponse;
import com.shopopedia.user.entity.User;
import com.shopopedia.user.entity.UserStatus;
import com.shopopedia.user.exception.UserAlreadyExistsException;
import com.shopopedia.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void registerUserPersistsContactAndEncodedPassword() {
        RegisterUserRequest request = new RegisterUserRequest(
                "John.Doe@Example.com",
                " John ",
                " 9876543210 ",
                "Secret123!",
                null
        );

        when(userRepository.existsByEmailIgnoreCase("john.doe@example.com")).thenReturn(false);
        when(passwordEncoder.encode("Secret123!")).thenReturn("encoded-password");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId(1L);
            return user;
        });

        UserResponse response = userService.registerUser(request);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertThat(savedUser.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(savedUser.getFirstName()).isEqualTo("John");
        assertThat(savedUser.getContact()).isEqualTo("9876543210");
        assertThat(savedUser.getPasswordHash()).isEqualTo("encoded-password");
        assertThat(savedUser.getLastName()).isNull();

        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.email()).isEqualTo("john.doe@example.com");
        assertThat(response.firstName()).isEqualTo("John");
        assertThat(response.contact()).isEqualTo("9876543210");
        assertThat(response.lastName()).isNull();
        assertThat(response.status()).isEqualTo(UserStatus.ACTIVE);

        verify(passwordEncoder).encode("Secret123!");
    }

    @Test
    void registerUserRejectsDuplicateEmail() {
        RegisterUserRequest request = new RegisterUserRequest(
                "john.doe@example.com",
                "John",
                "9876543210",
                "Secret123!",
                null
        );

        when(userRepository.existsByEmailIgnoreCase("john.doe@example.com")).thenReturn(true);

        assertThatThrownBy(() -> userService.registerUser(request))
                .isInstanceOf(UserAlreadyExistsException.class)
                .hasMessage("User already exists with email: john.doe@example.com");
    }
}
