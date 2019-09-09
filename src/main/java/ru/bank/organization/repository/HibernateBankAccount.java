package ru.bank.organization.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import ru.bank.organization.config.transaction.TransactionAbstraction;
import ru.bank.organization.entity.BankAccount;

import java.util.Arrays;

@Repository("hibernateBankAccount")
public class HibernateBankAccount implements BankAccountRepository {


    private static final Logger log = LoggerFactory.getLogger(HibernateBankAccount.class);


    private SessionFactory sessionFactory;


    @Autowired
    public HibernateBankAccount(@Qualifier("hibernateSessionFactory") SessionFactory sessionFactory
                                ) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long saveBankAccount(BankAccount bankAccount) {
        Long entityId = null;


        try (Session session = sessionFactory.openSession()) {
            System.out.println("before start tx : " + session.getTransaction().isActive());
            session.beginTransaction();
            entityId = (Long) session.save(bankAccount);
            session.getTransaction().commit();
            System.out.println("after commit tx : " + session.getTransaction().isActive());
        } catch (Exception e) {
            // TO DO
        }

        return entityId;
    }

    @Override
    @Cacheable(cacheNames = {"bankAccount"}, cacheManager = "simpleCacheManager", keyGenerator = "simpleKeyGenerator")
    public BankAccount getBankAccountById(Long bankId) {
        System.out.println("get bank account from repository");
        BankAccount bankAccount = null;

        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            bankAccount = session.get(BankAccount.class, bankId);
            session.getTransaction().commit();
        } catch (Exception e) {
            // TO DO
        }

        return bankAccount;
    }
}
