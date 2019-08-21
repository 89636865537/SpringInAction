package ru.bank.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.bank.organization.entity.Bank;
import ru.bank.organization.entity.BankAccount;
import ru.bank.organization.repository.BankAccountRepository;
import ru.bank.organization.repository.BankRepository;

import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    @Qualifier("hibernateBankAccount")
    BankAccountRepository bankAccountRepository;


//    public Bank getBank(String bankId) {
//        Optional<Long> bankIdOptional = Optional.of(bankId).map(Long::parseLong);
//        return bankRepository.getBankById(bankIdOptional.get()).get();
//    }


    public Long saveBank(BankAccount bankAccount) {
        return bankAccountRepository.saveBankAccount(Optional.of(bankAccount)).get();
    }
}
