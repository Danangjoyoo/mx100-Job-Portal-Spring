package com.mx.util;

public class HashPasswordChecker {
    public static Boolean check(String password, String hashedPassword){
        String hashedInputPassword = PasswordHashing.hash(password);
        return hashedInputPassword != null && hashedInputPassword.equals(hashedPassword);
    }
}
