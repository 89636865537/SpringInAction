package ru.bank.organization.config.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@PropertySource(value = "classpath:cache/simple.properties")
@ConfigurationProperties(prefix = "spring.cache")
public class SimpleCacheProperties {

    @Getter
    @Setter
    public List<String> cacheNames;

    public List<String> getCacheNames() {
        return cacheNames;
    }


}
