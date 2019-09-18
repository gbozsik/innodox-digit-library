package com.innodox.library.controller;

import com.innodox.library.dataobject.UserModel;
import com.innodox.library.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class LoginContorllerTest {

    private TestRestTemplate restTemplate;

    private LoginContorller loginContorller;

    @Mock
    private UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        restTemplate = new TestRestTemplate();
        loginContorller = new LoginContorller(userService);
    }

    @Test
    public void logout() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(loginContorller).build();
        MvcResult result = mockMvc.perform(get("/api/loggingout"))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo("Logged out");
    }

    @Test
    public void getActualUser() throws Exception {
        UserModel user = new UserModel();
        user.setUsername("username");
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(loginContorller).build();
        when(userService.getActualUser()).thenReturn(user);

        mockMvc.perform(get("/api/getactualuser"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"username\": \"username\"}"));
    }

    @Test
    public void getPing() {
        ResponseEntity<String> result = makeRestCallToPing("feri.sos@t-online.com", "pas");
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualTo("OK");
    }

    private ResponseEntity<String> makeRestCallToPing(String username, String pass) {
        return restTemplate.withBasicAuth(username, pass)
                .getForEntity("http://localhost:9000/api/ping", String.class, Collections.emptyMap());
    }

    private ResponseEntity<String> makeRestCallToPing() {
        return restTemplate.getForEntity("/api/ping", String.class, Collections.emptyMap());
    }
}