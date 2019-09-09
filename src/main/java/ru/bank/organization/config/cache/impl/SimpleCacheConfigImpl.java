package ru.bank.organization.config.cache.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.bank.organization.config.cache.CacheConfig;
import ru.bank.organization.config.properties.SimpleCacheProperties;

import java.util.List;
import java.util.stream.Collectors;


@Configuration
@EnableCaching
public class SimpleCacheConfigImpl implements CacheConfig {

    SimpleCacheProperties simpleCacheProperties;

    @Autowired
    public SimpleCacheConfigImpl(@Qualifier("simpleCacheProperties") SimpleCacheProperties simpleCacheProperties) {
        this.simpleCacheProperties = simpleCacheProperties;
    }

    @Override
    @Bean(value = "simpleCacheManager")
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();

        List<ConcurrentMapCache> concurrentMapCaches = simpleCacheProperties.getCacheNames().stream().map(s -> {
            return new ConcurrentMapCache(s);
        }).collect(Collectors.toList());
        concurrentMapCaches.stream().forEach((map) -> System.out.println(map.getName()));
        simpleCacheManager.setCaches(concurrentMapCaches);

        return simpleCacheManager;
    }

    @Override
    public CacheResolver cacheResolver() {
        return null;
    }

    @Override
    @Bean("simpleKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return null;
    }
}
