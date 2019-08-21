package ru.bank.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.bank.organization.entity.Bank;
import ru.bank.organization.repository.BankRepository;

import java.util.Optional;

@Service
public class BankService {

    @Autowired
    @Qualifier("JdbcBankRepository")
    BankRepository bankRepository;


    public Bank getBank(String bankId) {
        Optional<Long> bankIdOptional = Optional.of(bankId).map(Long::parseLong);
        return bankRepository.getBankById(bankIdOptional.get()).get();
    }


    public Long saveBank(Bank bank) {
        return bankRepository.saveBank(Optional.of(bank)).get();
    }
}
