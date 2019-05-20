/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pitang.api.pintang.repositories;

import com.pitang.api.pintang.entities.User;
import com.pitang.api.pintang.util.HashUtil;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

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
}
