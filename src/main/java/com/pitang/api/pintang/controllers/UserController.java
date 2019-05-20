/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pitang.api.pintang.controllers;

import com.pitang.api.pintang.config.jwt.JWTUtil;
import com.pitang.api.pintang.dto.LoginDto;
import com.pitang.api.pintang.dto.UserDto;
import com.pitang.api.pintang.entities.Phone;
import com.pitang.api.pintang.entities.User;
import com.pitang.api.pintang.services.PhoneService;
import com.pitang.api.pintang.services.UserService;
import com.pitang.api.pintang.util.Util;
import com.pitang.api.pintang.validation.UserValidation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author felly
 */
@RestController
public class UserController {
    
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    UserService us;
    
    @Autowired
    private UserValidation uv;   
    
    @Autowired
    private PhoneService ps;
    
    @PostMapping("signup")
    public ResponseEntity<String> signup(
            @Valid @RequestBody UserDto ud,
            BindingResult errors, 
            UriComponentsBuilder ucBuilder
    ){
        String result = "";
	if (errors.hasErrors()) {
           result = Util.formatErros(errors.getFieldErrors());
           return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}        
        try {
            User u = ud.toUser();
            u = us.persist(u);
            for (Phone p : ud.getPhones()) {
                p.setUser(u);
                ps.persist(p);
            }
            String token = JWTUtil.create(u.getEmail());
            result = token;
        } catch (DataIntegrityViolationException | HibernateException e) {
            result = e.getMessage();
            if(e.getClass().getName() == "org.springframework.dao.DataIntegrityViolationException"){
                result = "E-mail already exists";
            }
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @RequestMapping("signin")
    public ResponseEntity<String> signin(
            @Valid @RequestBody LoginDto ld,
            BindingResult errors, 
            UriComponentsBuilder ucBuilder
    ){
        String result = "";
	if (errors.hasErrors()) {
           result = Util.formatErros(errors.getFieldErrors());
           return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}    
        User u = null;
        try {
            u = us.findByEmailPassword(ld.getEmail(), ld.getPassword());
            String token = JWTUtil.create(u.getEmail());
            result = token;
        } catch (DataIntegrityViolationException | HibernateException e) {
            result = e.getMessage();
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }    
    
    
    @GetMapping("me")
    public String me(HttpServletResponse response, HttpServletRequest request){
        request.getHeader("Authorization");
        return "teste";
    }  

    @GetMapping("teste")
    public String teste(){
        return "teste";
    }    
    
}
