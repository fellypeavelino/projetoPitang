/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pitang.api.pintang.services;

import com.pitang.api.pintang.dto.UserDto;
import com.pitang.api.pintang.entities.User;
import com.pitang.api.pintang.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author felly
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository ur;
    
    public User persist(User u){
        return ur.persist(u);
    }
    
    public User findByEmailPassword(String email, String password){
        return ur.findByEmailPassword(email, password);
    }

    public UserDto findByEmail(String email) {
        return ur.findByEmail(email);
    }
}
