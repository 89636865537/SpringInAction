package ru.bank.organization.config.cache.impl;


import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import ru.bank.organization.config.cache.CacheConfig;

//@Configuration
public class HzCacheConfigImpl implements CacheConfig {

    private HazelcastInstance hazelcastInstance;

    @Bean
    private HazelcastInstance getHazelcastInstance() {
        Config clientConfig = new Config();
        Hazelcast.newHazelcastInstance(clientConfig);
        return hazelcastInstance;
    }


    @Override
    @Bean(value = "hazelcastCacheManager")
    public CacheManager cacheManager() {


        return new HazelcastCacheManager(getHazelcastInstance());
    }

    @Override
    public CacheResolver cacheResolver() {
        return null;
    }

    @Override
    @Bean
    public KeyGenerator keyGenerator() {

        return null;
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return null;
    }
}
