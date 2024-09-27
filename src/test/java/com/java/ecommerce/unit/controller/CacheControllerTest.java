package com.java.ecommerce.unit.controller;

import com.java.ecommerce.service.CacheService;
import com.java.ecommerce.web.controller.CacheController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CacheController.class)
class CacheControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CacheService cacheService;

    @Test
    void test_put_endpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/cache/put")
                        .param("key", "testKey")
                        .param("value", "testValue")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Added to cache."));
    }

    @Test
    void test_get_endpoint() throws Exception {
        when(cacheService.get("testKey")).thenReturn("testValue");

        mockMvc.perform(MockMvcRequestBuilders.get("/cache/get")
                        .param("key", "testKey")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("testValue"));
    }

    @Test
    void test_get_endpoint_when_cache_is_missed() throws Exception {
        when(cacheService.get("missingKey")).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/cache/get")
                        .param("key", "missingKey")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Cache miss!"));
    }

    @Test
    void test_evict_endpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/cache/evict")
                        .param("key", "testKey")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Evicted from cache."));
    }
}