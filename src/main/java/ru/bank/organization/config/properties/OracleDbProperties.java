package ru.bank.organization.config.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration

@PropertySource("classpath:db/oracle.properties")
@ConfigurationProperties(prefix = "oracledb")

public class OracleDbProperties {

    @Getter
    @Setter
    private String driver;
    @Getter
    @Setter
    private String url;
    @Getter
    @Setter
    private String userName;
    @Getter
    @Setter
    private String password;


}
