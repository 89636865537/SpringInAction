package ru.bank.organization.repository;


import org.springframework.stereotype.Repository;
import ru.bank.organization.entity.BankAccount;

@Repository
public interface BankAccountRepository {

    Long saveBankAccount(BankAccount bank);


    BankAccount getBankAccountById(Long bankId);

}
