package com.java.ecommerce.unit.cache;

import com.java.ecommerce.adapter.cache.InMemoryCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class InMemoryCacheTest {
    private InMemoryCache<String, String> cache;

    @BeforeEach
    void setUp() {
        cache = new InMemoryCache<>(1000); // 1 second TTL for testing
    }

    @Test
    void should_return_value_when_key_exists() {
        cache.put("key1", "value1");
        assertEquals("value1", cache.get("key1"));
    }

    @Test
    void should_return_null_when_entry_is_expired() throws InterruptedException {
        cache.put("key1", "value1");
        TimeUnit.MILLISECONDS.sleep(1100); // Wait for entry to expire
        assertNull(cache.get("key1"));
    }

    @Test
    void should_return_null_when_key_does_not_exist() {
        assertNull(cache.get("nonExistentKey"));
    }

    @Test
    void should_remove_entry_when_key_exists() {
        cache.put("key1", "value1");
        cache.evict("key1");
        assertNull(cache.get("key1"));
    }

    @Test
    void should_remove_expired_entries_when_called() throws InterruptedException {
        cache.put("key1", "value1");
        TimeUnit.MILLISECONDS.sleep(1100); // Wait for entry to expire
        ReflectionTestUtils.invokeMethod(cache, "evictExpiredEntries");
        assertNull(cache.get("key1"));
    }
}