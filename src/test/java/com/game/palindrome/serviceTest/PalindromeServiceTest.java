package com.game.palindrome.serviceTest;

import com.game.palindrome.service.PalindromeService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;

public class PalindromeServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnTrue_ifPalindrome() {
        PalindromeService palindromeService = new PalindromeService();
        boolean isPalindrome = palindromeService.isPalindrome("Madam 90*-+");
        Assert.assertTrue(isPalindrome);
    }

    @Test
    public void shouldReturnFalse_ifEmpty() {
        PalindromeService palindromeService = new PalindromeService();
        boolean isPalindrome = palindromeService.isPalindrome("");
        Assert.assertFalse(isPalindrome);
    }

    @Test
    public void shouldReturnFalse_ifNull() {
        PalindromeService palindromeService = new PalindromeService();
        boolean isPalindrome = palindromeService.isPalindrome(null);
        Assert.assertFalse(isPalindrome);
    }

    @Test
    public void shouldReturnTrue_ifUniquePalindrome() {
        PalindromeService palindromeService = new PalindromeService();

        Map<String,String> palindromesTable = new HashMap<String,String>() {{
            put("madam","Peter");
            put("loop","Kristina");
        }};
        boolean isUniquePalindrome = palindromeService.isUniquePalindrome(palindromesTable,"madam");
        Assert.assertFalse(isUniquePalindrome);
    }

}
