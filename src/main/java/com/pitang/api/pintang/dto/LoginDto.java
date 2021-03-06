/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pitang.api.pintang.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author felly
 */
@Data
public class LoginDto {
    @NotEmpty(message = "Missing fields")
    @NotNull(message = "Missing fields")
    private String email;
    @NotEmpty(message = "Missing fields")
    @NotNull(message = "Missing fields")
    private String password;     
}
