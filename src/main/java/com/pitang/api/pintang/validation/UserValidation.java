/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pitang.api.pintang.validation;

import com.pitang.api.pintang.entities.User;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author felly
 */
@Component
public class UserValidation  implements Validator { 
    
    private String invalidFields = "Invalid Fieds";

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "firstName", "fiel.required", this.invalidFields);
        ValidationUtils.rejectIfEmpty(errors, "lastName", "fiel.required", this.invalidFields);
        ValidationUtils.rejectIfEmpty(errors, "email", "fiel.required", this.invalidFields);
        ValidationUtils.rejectIfEmpty(errors, "password", "fiel.required", this.invalidFields);
        ValidationUtils.rejectIfEmpty(errors, "phone", "fiel.required", this.invalidFields);
    }


    
}
