package com.shopopedia.userservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserRequest {

    //2
    @Getter @Setter
    private String userId;
    //3
    @Getter@Setter
    private String firstName;
    //4
    @Getter@Setter
    private String lastName;
    //5
    @Getter@Setter
    private String emailId;
    //6
    @Getter@Setter
    private String mobileNo;
    //7
    @Getter@Setter
    private String password;
    //
    @Getter @Setter
    private String roleType;
}
