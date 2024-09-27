package com.java.ecommerce.service;

import com.java.ecommerce.adapter.cache.CacheAdapter;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService {
    private final CacheAdapter<String, String> cache;

    public CacheServiceImpl(CacheAdapter<String, String> cache) {
        this.cache = cache;
    }

    @Override
    public void put(String key, String value) {
        cache.put(key, value);
    }

    @Override
    public String get(String key) {
        return cache.get(key);
    }

    @Override
    public void evict(String key) {
        cache.evict(key);
    }
}
