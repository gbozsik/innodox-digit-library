package com.innodox.library.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;


public class LoginContorllerTest {

//    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = new TestRestTemplate();
    }

    @Test
    public void logout() {
    }

    @Test
    public void getActualUser() {
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