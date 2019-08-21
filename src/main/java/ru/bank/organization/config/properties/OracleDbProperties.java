package ru.bank.organization.config.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:db/oracle.properties")
@ConfigurationProperties(prefix = "oracledb")

public class OracleDbProperties extends CommonDbProperties {

}
