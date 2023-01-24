package com.usermanagement.usermanagementproducer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermanagement.usermanagementproducer.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void should_get_all_users() throws Exception {
        mockMvc.perform(get("/users")).andExpect(status().isOk())
                                                .andExpect(content().string(containsString("simo")));

    }

    @Test
    void should_addUser() throws Exception {

        User user= new User(1L
                            ,"userName"
                            ,"userEmail@test.com"
                            ,"simoQB24188*");
        mockMvc.perform(post("/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("userName")));
    }

    @Test
    void should_return_status_4XX_when_addUser_validation_fail() throws Exception {
        User user= new User(1L
                ,"userName"
                ,"userEmail@test.com"
                ,"userPassword");
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().is4xxClientError());
    }
}