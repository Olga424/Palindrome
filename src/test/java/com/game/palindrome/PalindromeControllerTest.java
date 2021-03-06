package com.game.palindrome;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
        mockMvc.perform(get("/palindrome/scores"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void returnTrue_ifSuccessfullyEnterPalindrome() throws Exception {
        mockMvc.perform(post("/palindrome/play/{username}/{string}", "Andrew", "madam"))
                .andExpect(status().isOk()).andReturn();
    }

}
