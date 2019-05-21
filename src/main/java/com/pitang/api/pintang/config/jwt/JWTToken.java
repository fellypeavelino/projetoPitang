/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pitang.api.pintang.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Calendar;

/**
 *
 * @author felly
 */
public class JWTToken {
    
    private static Key key = Keys.hmacShaKeyFor("!@#QWE!@#QWE!@#QWE!@#QWE!@#QWE!@#QWE!@#QWE!@#QWE!@#QWE!@#QWE".getBytes());

    public static final String TOKEN_HEADER = "Authentication";

    public static String create(String subject) {  
	Calendar expira = Calendar.getInstance();
	expira.add(Calendar.DAY_OF_MONTH, 1);
        return Jwts.builder()
                .setIssuer(subject)
                .signWith(key)
                .setExpiration(expira.getTime())
                .compact();
    }

    public static Jws<Claims> decode(String token){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token);
    }    
    
}
