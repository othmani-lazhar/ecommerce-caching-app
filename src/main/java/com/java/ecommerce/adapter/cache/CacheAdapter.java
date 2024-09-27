package com.java.ecommerce.adapter.cache;

import java.util.List;
import java.util.Map;

public interface CacheAdapter<K, V> {
    void put(K key, V value);

    V get(K key);

    void evict(K key);

    Map<K, V> getAll(List<K> keys);

    void putAll(Map<K, V> entries);

    void evictAll(List<K> keys);

    void clear();

    boolean containsKey(K key);

    boolean isEmpty();
}
