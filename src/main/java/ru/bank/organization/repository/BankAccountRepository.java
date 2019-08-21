package ru.bank.organization.repository;


import org.springframework.stereotype.Repository;
import ru.bank.organization.entity.Bank;
import ru.bank.organization.entity.BankAccount;

import java.util.Optional;

@Repository
public interface BankAccountRepository {

    Optional<Long> saveBankAccount(Optional<BankAccount> bank);


    Optional<BankAccount> getBankAccountById(Long bankId);

}
