/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pitang.api.pintang.util;

import java.math.BigInteger;

import java.security.MessageDigest;
import java.util.Base64;
/**
 *
 * @author felly
 */
public class HashUtil {
    public static String hashSenha = "WS_$#@!&";
    
    private static final String ALGORITMO = "AES/CBC/PKCS5Padding";    

    public static String getHash(String txt, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
            txt = hashSenha + txt;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(txt.getBytes(), 0, txt.length());
            BigInteger sb = new BigInteger(1, m.digest());

            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static String md5(String txt) {
        return HashUtil.getHash(txt, "MD5");
    }

    public static String sha1(String txt) {
        return HashUtil.getHash(txt, "SHA1");
    }    
    
    public static String criptografar(String mensagem) throws Exception {

        String cript = mensagem;
        byte[] encodedBytes = Base64.getEncoder().encode(cript.getBytes());
        return new String(encodedBytes);

    }

    public static String descriptografar(String mensagem) throws Exception {

        byte[] decodedBytes = Base64.getDecoder().decode(mensagem.getBytes());
        String decoded = new String(decodedBytes);
        
        return decoded;
    }
    
}
