package com.instituto.cuanto.sisgene.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 *
 * @author Jhony Monzalve
 */
public class Criptografo {
    public String encripta(String cadena) {
        StandardPBEStringEncryptor s = new StandardPBEStringEncryptor();
        s.setPassword("uniquekey");
        
        return s.encrypt(cadena);
    }

    public String desencripta(String cadena) {
        StandardPBEStringEncryptor s = new StandardPBEStringEncryptor();
        s.setPassword("uniquekey");
        String devuelve = "";
        try {
            devuelve = s.decrypt(cadena);
        } catch (Exception e) {
        }
        return devuelve;
    }
}
