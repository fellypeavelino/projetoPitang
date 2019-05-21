/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pitang.api.pintang.repositories;

import com.pitang.api.pintang.entities.Phone;
import com.pitang.api.pintang.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author felly
 */
@Repository
@Transactional
public class PhoneRepository {
    
    @PersistenceContext
    private EntityManager em; 
    
    @Autowired
    JdbcTemplate jdbcTemplate;    
    
    public Phone persist(Phone p){
        return (Phone) em.merge(p);
    }    
    
    public List<Phone> findByUser(User u){
        List<Phone> list = null;
        try {
            list = jdbcTemplate.query(
                "SELECT id, number, area_code, contry_code FROM phone WHERE user_id = ?", new Object[] { u.getId() },
                (rs, rowNum) -> new Phone(rs.getLong("id"),
                        rs.getInt("number"), rs.getInt("area_code"), 
                        rs.getString("contry_code"), u)
            );            
        } catch (Exception e) {
            list = new ArrayList<>();
        }
        return list;
    }     
}
