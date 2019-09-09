package ru.bank.organization.config.db.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.bank.organization.config.db.DataBaseAbstraction;
import ru.bank.organization.config.properties.OracleDbProperties;

import javax.sql.DataSource;
import java.util.stream.Stream;

@Configuration
public class JdbcConfigImpl implements DataBaseAbstraction {


    OracleDbProperties oracleDbProperties;

    @Autowired
    public JdbcConfigImpl(@Qualifier(value = "oracleDbProperties") OracleDbProperties oracleDbProperties){
        this.oracleDbProperties = oracleDbProperties;
    }


    @Override
    @Bean(name = "dataSourceJdbc")
    public DataSource dataSource() {

        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(oracleDbProperties.getDriver());
        driverManagerDataSource.setUrl(oracleDbProperties.getUrl());
        driverManagerDataSource.setUsername(oracleDbProperties.getUserName());
        driverManagerDataSource.setPassword(oracleDbProperties.getPassword());
        Stream.builder().add(driverManagerDataSource.getSchema()).add(driverManagerDataSource.getUrl()).add(driverManagerDataSource.getPassword()).add(driverManagerDataSource.getUsername()).add(driverManagerDataSource).build().forEach(System.out::println);
        return driverManagerDataSource;
    }


}
