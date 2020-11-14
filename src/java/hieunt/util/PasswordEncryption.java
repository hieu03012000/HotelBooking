/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieunt.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author HIEUNGUYEN
 */
public class PasswordEncryption {

    private byte[] getSHA(String intput) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(intput.getBytes(StandardCharsets.UTF_8));
    }

    private String toHexString(byte[] input) {
        BigInteger num = new BigInteger(1, input);
        StringBuilder hexString = new StringBuilder(num.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

    public String toSHAString(String input) throws NoSuchAlgorithmException {
        return toHexString(getSHA(input));
    }
}
