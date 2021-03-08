package com.game.palindrome.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PalindromeService {

    public boolean isPalindrome(String string) {

        if (string == null || string.equals("")) {
            return false;
        }
        String palindrome = string.replaceAll("[^A-Za-z]", "").toLowerCase();
        StringBuilder reversePalindrome = new StringBuilder();
        for (int i = palindrome.length()-1; i >= 0; i--) {
            reversePalindrome.append(palindrome.charAt(i));
        }
        return palindrome.equals(reversePalindrome.toString());

    }

    public boolean isUniquePalindrome(Map<String, String> palindromesTable, String string) {

        if (string == null) {
            return false;
        }
        String palindrome = string.replaceAll("[^a-zA-Z]", "").toLowerCase();
        for (Map.Entry<String, String> pair : palindromesTable.entrySet()) {
            if (pair.getKey().equals(palindrome)) {
                return false;
            }
        }
        return true;

    }

}
