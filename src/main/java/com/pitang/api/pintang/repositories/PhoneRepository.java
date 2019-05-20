/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pitang.api.pintang.repositories;

import com.pitang.api.pintang.entities.Phone;
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
public class PhoneRepository {
    
    @PersistenceContext
    private EntityManager em; 
    
    public Phone persist(Phone p){
        return (Phone) em.merge(p);
    }    
}
