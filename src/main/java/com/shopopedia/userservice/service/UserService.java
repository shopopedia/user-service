package com.shopopedia.userservice.service;

import com.shopopedia.userservice.domain.User;
import com.shopopedia.userservice.mapper.UserMapper;
import com.shopopedia.userservice.model.UserRequest;
import com.shopopedia.userservice.model.UserResponse;
import com.shopopedia.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserMapper mapper;

    @Autowired
    UserRepository userRepository;
    public UserResponse create(UserRequest userRequest) {

        User newUser = null;
        try{
            newUser = mapper.userRequestMapper(userRequest);
            newUser = userRepository.save(newUser);

        }catch (Exception e){
            logger.error(" Exception Occurred! : "+e);
        }
        return mapper.userResponseMapper(newUser);
    }

    public Optional<UserResponse> get(String id) {
        Optional<User> user = userRepository.findByUserId(id);

        if(user.isPresent()) {
            return Optional.of(mapper.userResponseMapper(user.get()));
        }else {
            return Optional.empty();
        }
    }

//    public UserResponse login(UserRequest userRequest) { }
}