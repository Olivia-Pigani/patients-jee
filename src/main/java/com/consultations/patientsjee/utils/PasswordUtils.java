package com.consultations.patientsjee.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;

public class PasswordUtils {

    public static byte[] getSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static byte[] hashPassword(final char[] password, final byte[] salt, final int iterations, final int hashLength){
        try {
            PBEKeySpec spec = new PBEKeySpec(password,salt,iterations,hashLength);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return secretKeyFactory.generateSecret(spec).getEncoded();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
