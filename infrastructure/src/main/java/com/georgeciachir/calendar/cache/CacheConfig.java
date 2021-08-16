package com.georgeciachir.calendar.cache;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    private static final long CACHE_EXPIRY_IN_HOURS = 1;
    private static final String WEATHER_FORECAST = "weatherForecast";

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(List.of(
                buildCache(WEATHER_FORECAST)
        ));
        return cacheManager;
    }

    private Cache buildCache(String cacheName) {
        return new ConcurrentMapCache(
                cacheName,
                CacheBuilder.newBuilder()
                        .expireAfterWrite(CACHE_EXPIRY_IN_HOURS, TimeUnit.HOURS)
                        .build()
                        .asMap(),
                false);
    }
}
