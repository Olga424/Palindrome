package com.game.palindrome.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    public int calculateScore(int currentScore, String string) {
        return currentScore + string.length() / 2;
    }

    public void registerUser(Map<String, Integer> scoreTable, String username) {
        if (!scoreTable.containsKey(username)) {
            scoreTable.put(username, 0);
        }
    }

}
