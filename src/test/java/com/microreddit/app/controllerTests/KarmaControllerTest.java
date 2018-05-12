package com.microreddit.app.controllerTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Josh Harkema
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KarmaControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    private String userID;
    private String postID;
    private String vote;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .build();
    }

    @Test
    public void testKarmaPut() throws Exception {
        this.userID = "8864b0eb-6bf2-4960-b25a-28d09e017add";
        this.postID = "8864b0eb-6bf2-4960-b25a-28d09e017add";
        this.vote = "up";

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/vote/post")
                .param("userID", this.userID)
                .param("postID", this.postID)
                .param("vote", this.vote);

        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Override
    public String toString() {
        return "{\"userID\":" + "\"" + userID +
                "\", \"postID\":" + "\"" + postID +
                "\", \"vote\":" + "\"" + vote + "\"}";
    }
}
