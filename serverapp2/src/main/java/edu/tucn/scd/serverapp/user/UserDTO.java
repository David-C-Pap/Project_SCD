package edu.tucn.scd.serverapp.user;

import lombok.Data;

@Data // lombok annotation, creates equals(), hashCode() and toString at compile time
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
}
