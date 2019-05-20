/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pitang.api.pintang.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

/**
 *
 * @author felly
 */
@Entity
@Data
public class Phone {

    public Phone(){}
    
    public Phone(Long id, Integer number, Integer areaCode, String contryCode, User user) {
        this.id = id;
        this.number = number;
        this.area_code = areaCode;
        this.contry_code = contryCode;
        this.user = user;
    }
    
    @Id @GeneratedValue
    private Long id; 
    @Column(nullable=false)
    private Integer number;
    @Column(nullable=false)
    private Integer area_code;
    private String contry_code;
    @ManyToOne
    private User user;
}
