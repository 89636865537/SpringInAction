package ru.bank.organization.config.transaction.impl;


import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import ru.bank.organization.config.transaction.TransactionAbstraction;
import ru.bank.organization.repository.HibernateBankAccount;

@Configuration
public class HibernateTransactionImpl implements TransactionAbstraction {


    private static final Logger log = LoggerFactory.getLogger(HibernateTransactionImpl.class);

    SessionFactory sessionFactory;


    @Autowired
    public HibernateTransactionImpl(@Qualifier("hibernateSessionFactory") SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    @Bean(value = "hibernateTransactionManager")
    public PlatformTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);


        return transactionManager;
    }


}
