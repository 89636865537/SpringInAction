package ru.bank.organization.config.transaction.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import ru.bank.organization.config.transaction.TransactionAbstraction;

import javax.sql.DataSource;

@Configuration
public class JdbcTransactionImpl implements TransactionAbstraction {


    DataSource dataSource;

    @Autowired
    public JdbcTransactionImpl(@Qualifier("dataSourceJdbc") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    @Bean(value = "jdbcTransactionManager")
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);

        return transactionManager;
    }

    @Bean(value = "transactionTemplate")
    public TransactionTemplate transactionTemplate(){
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(transactionManager());
        return transactionTemplate;
    }
}
