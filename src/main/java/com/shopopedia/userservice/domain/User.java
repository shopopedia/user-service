package com.shopopedia.userservice.domain;

import com.shopopedia.userservice.Helper.Util;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("user")
public class User {
//1
    @Id
    private String id;
//2
    //@Getter @Setter
    private String userId = Util.getUserId();
//3
    //@Getter@Setter
    private String firstName;
//4
    //@Getter@Setter
    private String lastName;
//5
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
    private String phoneNo;
//9
    //@Getter @Setter
    private String roleType;
    
}