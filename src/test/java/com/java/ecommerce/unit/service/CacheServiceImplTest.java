package com.java.ecommerce.unit.service;

import com.java.ecommerce.adapter.cache.CacheAdapter;
import com.java.ecommerce.service.CacheServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CacheServiceImplTest {
    @Mock
    private CacheAdapter<String, String> cacheAdapter;
    @InjectMocks
    private CacheServiceImpl cacheService;


    @Test
    void should_store_value_when_key_and_value_are_provided() {
        cacheService.put("key1", "value1");
        verify(cacheAdapter).put("key1", "value1");
    }

    @Test
    void should_return_value_when_key_exists() {
        when(cacheAdapter.get("key1")).thenReturn("value1");
        assertEquals("value1", cacheService.get("key1"));
    }

    @Test
    void should_return_null_when_key_does_not_exist() {
        when(cacheAdapter.get("nonExistentKey")).thenReturn(null);
        assertNull(cacheService.get("nonExistentKey"));
    }

    @Test
    void should_remove_entry_when_key_exists() {
        cacheService.evict("key1");
        verify(cacheAdapter).evict("key1");
    }
}