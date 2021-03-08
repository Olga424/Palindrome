package com.game.palindrome.serviceTest;

import com.game.palindrome.service.UserService;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class UserServiceTest {

    @Test
    public void shouldReturnTrue_ifCalculatesScore() {
        UserService userService = new UserService();
        int expectedScore = userService.calculateScore(0, "madam");
        Assert.assertEquals(2,expectedScore);
    }

    @Test
    public void shouldReturnTrue_ifRegisterNewUser(){
        Map<String, Integer> scoreTable = new HashMap<>();
        UserService userService = new UserService();
        userService.registerUser(scoreTable, "Alice");
        Assert.assertTrue(scoreTable.containsKey("Alice"));
        Assert.assertEquals(0, (int) scoreTable.get("Alice"));
        Assert.assertFalse(scoreTable.containsKey("Vladimir"));
    }

    @Test
    public void shouldReturnTrue_ifUserAlreadyRegistered(){
        Map<String, Integer> scoreTable = new HashMap<String,Integer>() {{
            put("Julia",22);
            put("Vladimir",8);
        }};

        UserService userService = new UserService();
        userService.registerUser(scoreTable,"Julia");
        Assert.assertTrue(scoreTable.containsKey("Julia"));
        Assert.assertFalse(scoreTable.containsKey("Max"));
    }
}
