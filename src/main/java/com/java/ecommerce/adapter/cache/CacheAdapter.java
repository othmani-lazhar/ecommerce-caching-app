package com.java.ecommerce.adapter.cache;

public interface CacheAdapter<K, V> {
    void put(K key, V value);

    V get(K key);

    void evict(K key);
}
