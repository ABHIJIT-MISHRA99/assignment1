package com.am.assignment1.service;

import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class customernamegeneratorservice {
    private static final String vowels = "aeiou";
    private static final String consonants = "bcdfghjklmnpqrstvwxyz";

    public String generateName() {
        return generateFourLetterString(4) + " " + generateFourLetterString(4);
    }

    private String generateFourLetterString(Integer len) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (i % 2 == 0) {
                sb = sb.append(consonants.charAt(r.nextInt(consonants.length())));
            } else {
                sb = sb.append(vowels.charAt(r.nextInt(vowels.length())));
            }
        }
        return capitalizeInitial(sb.toString());
    }

    private String capitalizeInitial(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
