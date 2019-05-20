/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pitang.api.pintang.dto;

import com.pitang.api.pintang.entities.Phone;
import com.pitang.api.pintang.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author felly
 */
@Data
public class UserDto {
    @NotEmpty(message = "Missing fields")
    @NotNull(message = "Missing fields")
    private String firstName;
    @NotEmpty(message = "Missing fields")
    @NotNull(message = "Missing fields")
    private String lastName;
    @NotEmpty(message = "Missing fields")
    @NotNull(message = "Missing fields")
    private String email;
    @NotEmpty(message = "Missing fields")
    @NotNull(message = "Missing fields")
    private String password;  
    @NotEmpty(message = "Missing fields")
    @NotNull(message = "Missing fields")
    private List<Phone> phones;
    
    public User toUser(){
        return new User(firstName, lastName, email, password);
    }

}
