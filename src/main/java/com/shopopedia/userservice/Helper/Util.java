package com.shopopedia.userservice.Helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Util {

    public static String getUserId(){
        String randomNum="";

        try {
            //Initialize SecureRandom
            //This is a lengthy operation, to be done only upon
            //initialization of the application
            SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");

            //generate a random number
            randomNum = Integer.valueOf(prng.nextInt()).toString();

            //get its digest
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] result =  sha.digest(randomNum.getBytes());

            System.out.println("Random number: " + randomNum);
            //System.out.println("Message digest: " + hexEncode(result));
        }
        catch (NoSuchAlgorithmException ex) {
            System.err.println(ex);
        }
        return randomNum;
    }
}
