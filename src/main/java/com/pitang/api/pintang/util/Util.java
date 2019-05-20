/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pitang.api.pintang.util;

import java.util.List;
import org.springframework.validation.FieldError;

/**
 *
 * @author felly
 */
public class Util {
    
    public static String formatErros(List<FieldError> listErrors){
        String error = "";
        for (FieldError listError : listErrors) {
            error += listError.getField()+": "+listError.getDefaultMessage()+"\n";
        } 
        return error;
    }
    
}
