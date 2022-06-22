package com.shopopedia.userservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class UserResponse {

    //2
    @Indexed(unique = true)
    //@Getter @Setter
    private String userId;
    //3
    //@Getter@Setter
    private String firstName;
    //4
    //@Getter@Setter
    private String lastName;
    //5
    @Indexed(unique = true)
    //@Getter@Setter
    private String emailId;
    //6
    //@Getter@Setter
    private String mobileNo;
    //7
    //@Getter@Setter
    private String password;
    //8
    //@Getter @Setter
    private String roleType;
}