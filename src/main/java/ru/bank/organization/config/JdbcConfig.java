package ru.bank.organization.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.bank.organization.config.properties.CommonDbProperties;

import javax.sql.DataSource;
import java.util.stream.Stream;

@Configuration
@ComponentScan(basePackages = "ru.bank.organization")
public class JdbcConfig {

    private CommonDbProperties commonDbProperties;

    @Autowired
    @Qualifier(value = "oracleDbProperties")
    @Bean(name = "dataSource")
    public DataSource getDataSource(CommonDbProperties commonDbProperties) {

        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(commonDbProperties.getDriver());
        driverManagerDataSource.setUrl(commonDbProperties.getUrl());
        driverManagerDataSource.setUsername(commonDbProperties.getUserName());
        driverManagerDataSource.setPassword(commonDbProperties.getPassword());
        Stream.builder().add(driverManagerDataSource.getSchema()).add(driverManagerDataSource.getUrl()).add(driverManagerDataSource.getPassword()).add(driverManagerDataSource.getUsername()).add(driverManagerDataSource).build().forEach(System.out::println);
        return driverManagerDataSource;
    }


}
