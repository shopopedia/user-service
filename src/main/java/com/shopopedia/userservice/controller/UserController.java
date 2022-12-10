package com.shopopedia.userservice.controller;

import com.shopopedia.userservice.model.UserRequest;
import com.shopopedia.userservice.model.UserResponse;
import com.shopopedia.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping("/servertest")
    public ResponseEntity<String> testSecurity(){
        return ResponseEntity.ok("OK");
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest){
        logger.info(" : UserRequest : "+userRequest);

        UserResponse userResponse = null;
        try {
            userResponse = userService.create(userRequest);
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }
        return ResponseEntity.ok(userResponse);
    }

//TODO
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest){
        logger.info(" ::::: UserRequest ::::: "+userRequest);

        UserResponse userResponse = null;
        try {
            //userResponse = userService.login(userRequest);
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }
        return ResponseEntity.ok(userResponse);
    }
    
    @GetMapping("/userId/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String id){
        logger.info(" ::::: id ::::: "+id);

        Optional<UserResponse> userResponse = userService.get(id);

        if (userResponse.isPresent())
            return ResponseEntity.ok(userResponse.get());
        else
            return ResponseEntity.notFound().build();
    }

}
