/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pitang.api.pintang.services;

import com.pitang.api.pintang.entities.Phone;
import com.pitang.api.pintang.repositories.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author felly
 */
@Service
public class PhoneService {
    @Autowired
    PhoneRepository pr;
    
    public Phone persist(Phone p){
        return pr.persist(p);
    }
}
