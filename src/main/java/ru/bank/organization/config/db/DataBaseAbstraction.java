package ru.bank.organization.config.db;


import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public interface DataBaseAbstraction {


    public DataSource dataSource();
}
