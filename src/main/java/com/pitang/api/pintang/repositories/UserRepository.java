/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pitang.api.pintang.repositories;

import com.pitang.api.pintang.dto.UserDto;
import com.pitang.api.pintang.entities.User;
import com.pitang.api.pintang.util.HashUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author felly
 */
@Repository
@Transactional
public class UserRepository {
    
    HashUtil hash = new HashUtil();
    
    @PersistenceContext
    private EntityManager em; 
    
    @Autowired
    private PhoneRepository pr;
    
    public User persist(User u){
        String pass = this.hash.md5(u.getPassword());
        u.setPassword(pass);
        return (User) em.merge(u);
    }
    
    public User findByEmailPassword(String email, String password){
        String pass = this.hash.md5(password);
        User u = null;
        try {
            u = (User) em.createQuery("select u from User u where u.email=:email and u.password = :password")
                .setParameter("email", email)
                .setParameter("password", pass)
                .getSingleResult();
        } catch (Exception e) {
            u = new User();
        }
        return u;
    } 
    
    
    public UserDto findByEmail(String email){
        User u = null;
        
        UserDto dto = null;
        try {
            u = (User) em.createQuery("select u from User u where u.email=:email")
                .setParameter("email", email)
                .getSingleResult();
            if (u != null) {
                dto = new UserDto(u.getFirstName(), u.getLastName(), u.getEmail(), u.getPassword());
                dto.setPhones(this.pr.findByUser(u));
            }
        } catch (Exception e) {
            u = new User();
        }
        return dto;
    }     
}
