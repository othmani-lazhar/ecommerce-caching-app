package com.java.ecommerce.service;

public interface CacheService {
    void put(String key, String value);

    String get(String key);

    void evict(String key);
}
