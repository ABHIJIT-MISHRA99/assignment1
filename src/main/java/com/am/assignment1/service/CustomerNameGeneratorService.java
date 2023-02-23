package com.am.assignment1.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class CustomerNameGeneratorService {
    private static final String vowels = "aeiou";
    private static final String consonants = "bcdfghjklmnpqrstvwxyz";

    private List<String> names=new ArrayList<>();
    private int count=0;

    public List<String> getNames() {
        names.add("abcd");
        names.add("abcd1");
        names.add("abcd2");
        names.add("abcd3");
        names.add("abcd4");
        return  names;
    }
    public int getNameCount(List<String> names){
        for(String w:names){
            count+=1;
        }
        return count;
    }

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
