package com.college.yi.bookmanager.utill;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "user123";
        String encodedPassword = encoder.encode(password);
        System.out.println(encodedPassword);
    }
}