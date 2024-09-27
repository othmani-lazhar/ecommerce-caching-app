package com.java.ecommerce.web.controller;

import com.java.ecommerce.service.CacheService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
public class CacheController {
    private final CacheService cacheService;

    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @PostMapping("/put")
    public String put(@RequestParam String key, @RequestParam String value) {
        cacheService.put(key, value);
        return "Added to cache.";
    }

    @GetMapping("/get")
    public String get(@RequestParam String key) {
        String value = cacheService.get(key);
        return value != null ? value : "Cache miss!";
    }

    @DeleteMapping("/evict")
    public String evict(@RequestParam String key) {
        cacheService.evict(key);
        return "Evicted from cache.";
    }
}