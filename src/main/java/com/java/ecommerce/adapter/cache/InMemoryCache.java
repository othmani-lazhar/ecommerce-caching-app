package com.java.ecommerce.adapter.cache;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class InMemoryCache<K, V> implements CacheAdapter<K, V> {
    private final Map<K, CacheValue<V>> cacheMap = new ConcurrentHashMap<>();
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    private final long ttl;

    public InMemoryCache(@Value("${cache.ttl:6000}") long ttl) {
        this.ttl = ttl;
        executorService.scheduleAtFixedRate(this::evictExpiredEntries, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public void put(K key, V value) {
        long expiryTime = System.currentTimeMillis() + ttl;
        cacheMap.put(key, new CacheValue<>(value, expiryTime));
    }

    @Override
    public V get(K key) {
        CacheValue<V> cacheValue = cacheMap.get(key);
        if (cacheValue == null || System.currentTimeMillis() > cacheValue.getExpiryTime()) {
            cacheMap.remove(key);
            return null;
        }
        return cacheValue.getValue();
    }

    @Override
    public void evict(K key) {
        cacheMap.remove(key);
    }

    private void evictExpiredEntries() {
        long currentTime = System.currentTimeMillis();
        cacheMap.entrySet().removeIf(entry -> currentTime > entry.getValue().getExpiryTime());
    }

    private static class CacheValue<V> {
        private final V value;
        private final long expiryTime;

        CacheValue(V value, long expiryTime) {
            this.value = value;
            this.expiryTime = expiryTime;
        }

        public V getValue() {
            return value;
        }

        public long getExpiryTime() {
            return expiryTime;
        }
    }
}

