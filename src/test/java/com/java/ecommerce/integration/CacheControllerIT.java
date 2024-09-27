package com.java.ecommerce.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CacheControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testPutAndGet() {
        restTemplate.postForEntity("/cache/put?key=testKey&value=testValue", null, String.class);

        ResponseEntity<String> response = restTemplate.getForEntity("/cache/get?key=testKey", String.class);
        assertEquals("testValue", response.getBody());
    }

    @Test
    public void testCacheMiss() {
        ResponseEntity<String> response = restTemplate.getForEntity("/cache/get?key=nonExistentKey", String.class);
        assertEquals("Cache miss!", response.getBody());
    }
}
