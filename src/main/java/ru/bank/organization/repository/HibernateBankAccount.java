package ru.bank.organization.repository;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.bank.organization.entity.Bank;
import ru.bank.organization.entity.BankAccount;

import java.util.Optional;

@Repository("hibernateBankAccount")
public class HibernateBankAccount implements BankAccountRepository {

    private static final Logger log = LoggerFactory.getLogger(HibernateBankAccount.class);

    @Autowired
    @Qualifier("sessionFactory")
    SessionFactory sessionFactory;

    @Override
    public Optional<Long> saveBankAccount(Optional<BankAccount> bankAccount) {
        sessionFactory.openSession().save(bankAccount.get());
        return  Optional.of(new Long(2));
    }

    @Override
    public Optional<BankAccount> getBankAccountById(Long bankId) {
        return Optional.empty();
    }
}
