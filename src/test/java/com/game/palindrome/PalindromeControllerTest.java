package com.game.palindrome;

import com.game.palindrome.PalindromeController;
import com.game.palindrome.PalindromeService;
import com.game.palindrome.UserService;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PalindromeControllerTest {

    @Mock
    private PalindromeService palindromeService;
    @Mock
    private UserService userService;

    @InjectMocks
    private PalindromeController palindromeController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(palindromeController).build();
    }

    @Test
    public void returnTrue_ifSuccessfullyGetScores() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/palindrome/scores"))
                .andExpect(status().isOk()).andReturn();
        Assert.assertEquals("", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void returnTrue_ifSuccessfullyEnterPalindrome() throws Exception {
        //MvcResult mvcResult = mockMvc.perform(put("/some/uri/{foo}/{bar}", "foo", "bar"))
                //.andExpect(status().isOk()).andReturn();
        //Assert.assertEquals("foo and bar",



        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/palindrome/play/{username}/{string}", "Vladislav", "madam"))
                .andExpect(status().isOk()).andReturn();

                boolean isPalindrome = palindromeService.isPalindrome("madam");

                Assert.assertEquals("NOT A PALIDROME",mvcResult.getResponse().getContentAsString());
        //Assert.assertEquals("CONGRATULATIONS" + username + "You earned " + string.length() / 2 + "points. Your score is" + totalScore)
    }
}
