package ru.bank.organization.config.transaction;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public interface TransactionAbstraction {

    @Bean
    public PlatformTransactionManager transactionManager();
}
