package ru.bank.organization.repository;


import org.springframework.stereotype.Repository;
import ru.bank.organization.entity.Bank;

import java.util.Optional;

@Repository
public interface BankRepository {

    Optional<Long> saveBank(Optional<Bank> bank);


    Optional<Bank> getBankById(Long bankId);


}
