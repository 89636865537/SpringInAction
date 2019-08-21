package ru.bank.organization.config;


import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import ru.bank.organization.config.properties.CommonDbProperties;

import javax.sql.DataSource;

@Configuration

public class HibernateConfig {
    @Autowired
    @Qualifier(value =  "oracleDbProperties")
    CommonDbProperties commonDbProperties;


    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(commonDbProperties.getDriver());
        dataSource.setUrl(commonDbProperties.getUrl());
        dataSource.setUsername(commonDbProperties.getUserName());
        dataSource.setPassword(commonDbProperties.getPassword());
        return dataSource;
    }

    @Bean(value = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("ru.bank.organization.entity");
        sessionFactory.setConfigLocation(new ClassPathResource("hibernate/hibernate.properties"));

        return sessionFactory;
    }


    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

}
