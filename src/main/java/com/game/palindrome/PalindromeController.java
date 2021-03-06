package com.game.palindrome;

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

    Map<String, String> palindromesTable = new HashMap<>();
    Map<String, Integer> scoreTable = new HashMap<>();

    public PalindromeController(UserService userService, PalindromeService palindromeService) {
        this.userService = userService;
        this.palindromeService = palindromeService;
    }

    @PostMapping("/play/{username}/{string}")
    public ResponseEntity<String> enterPalindrome(@PathVariable("username") String username, @PathVariable("string") String string) {

        if (palindromeService.isPalindrome(string)) {

            if (palindromeService.isUniquePalindrome(palindromesTable, string)) {
                palindromesTable.put(string, username);
            } else {
                return new ResponseEntity<>("ALREADY IN USE. TRY AGAIN", HttpStatus.OK);
            }

            userService.registerUser(scoreTable, username);

            int currentScore = scoreTable.get(username);
            int totalScore = userService.calculateScore(currentScore,string);
            scoreTable.put(username, totalScore);

            return new ResponseEntity<>("CONGRATULATIONS" + username + "You earned " + string.length() / 2 + "points. Your score is" + totalScore, HttpStatus.OK);
        }

        return new ResponseEntity<>("NOT A PALIDROME", HttpStatus.OK);
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