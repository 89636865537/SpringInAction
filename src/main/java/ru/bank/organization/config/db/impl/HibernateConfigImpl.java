package ru.bank.organization.config.db.impl;


import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.dialect.Oracle12cDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import ru.bank.organization.config.db.DataBaseAbstraction;
import ru.bank.organization.config.properties.OracleDbProperties;
import ru.bank.organization.entity.BankAccount;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateConfigImpl implements DataBaseAbstraction {



    OracleDbProperties oracleDbProperties;

    @Autowired
    public HibernateConfigImpl(@Qualifier(value = "oracleDbProperties") OracleDbProperties oracleDbProperties){
        this.oracleDbProperties = oracleDbProperties;
    }






    @Bean(value = "dataSourceHibernate")
    @Override
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(oracleDbProperties.getDriver());
        dataSource.setUrl(oracleDbProperties.getUrl());
        dataSource.setUsername(oracleDbProperties.getUserName());
        dataSource.setPassword(oracleDbProperties.getPassword());
        return dataSource;
    }

    @Bean(value = "hibernateSessionFactory")
    public LocalSessionFactoryBean sessionFactory() {

        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setAnnotatedClasses(BankAccount.class);

        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create");

        properties.setProperty("hibernate.dialect", Oracle12cDialect.class.getName());

        sessionFactoryBean.setHibernateProperties(properties);
//        sessionFactory.setConfigLocation(new ClassPathResource("hibernate/hibernate.properties"));


        return sessionFactoryBean;
    }


}
