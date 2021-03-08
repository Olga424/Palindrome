package com.game.palindrome.controller;

import com.game.palindrome.service.PalindromeService;
import com.game.palindrome.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/palindrome")
public class PalindromeController {

    private final UserService userService;
    private final PalindromeService palindromeService;

    /* returns HashMap of all entered palindromes */
    Map<String, String> palindromesTable = new HashMap<>();
    /* returns HashMap of all users and their total score */
    Map<String, Integer> scoreTable = new HashMap<>();

    public PalindromeController(UserService userService, PalindromeService palindromeService) {
        this.userService = userService;
        this.palindromeService = palindromeService;
    }

    @PostMapping("/play/{username}/{string}")
    public ResponseEntity<String> enterPalindrome(@PathVariable("username") String username, @PathVariable("string") String string) {

        /*check if the word is a palindrome*/
        if (palindromeService.isPalindrome(string)) {

            /*check if the word was already used by another user*/
            if (palindromeService.isUniquePalindrome(palindromesTable, string)) {
                palindromesTable.put(string, username);
            } else {
                return new ResponseEntity<>("Already in use. Try again", HttpStatus.OK);
            }

            /*add the username and his/her points into Map<String, Integer> scoreTable */
            userService.registerUser(scoreTable, username);

            int currentScore = scoreTable.get(username);
            int totalScore = userService.calculateScore(currentScore,string);
            scoreTable.put(username, totalScore);

            return new ResponseEntity<>("Good job " + username + "! You earned " + string.length() / 2 + " points. Your score is" + totalScore, HttpStatus.OK);
        }

        return new ResponseEntity<>("Not a palindrome. Try again", HttpStatus.OK);
    }

    @GetMapping("/scores")
    public ResponseEntity<Map<String, Integer>> getScores() {

        Map<String, Integer> sortedScoreTable = scoreTable.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (e1, e2) -> e1, LinkedHashMap::new));
        return new ResponseEntity<>(sortedScoreTable, HttpStatus.OK);

    }
}
