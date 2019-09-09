package ru.bank.organization.service;

import com.sun.org.apache.bcel.internal.generic.ARETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import ru.bank.organization.entity.Bank;
import ru.bank.organization.repository.BankRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BankService {


    BankRepository bankRepository;
    TransactionTemplate transactionTemplate;

    @Autowired
    public BankService(@Qualifier("JdbcBankRepository") BankRepository bankRepository,
                       @Qualifier("transactionTemplate") TransactionTemplate transactionTemplate) {
        this.bankRepository = bankRepository;
        this.transactionTemplate = transactionTemplate;
    }

    public Bank getBank(String bankId) {
        Optional<Long> bankIdOptional = Optional.of(bankId).map(Long::parseLong);
        return bankRepository.getBankById(bankIdOptional.get()).get();
    }


    public Long saveBank(Bank bank) {
        Long result = transactionTemplate.execute(transactionStatus -> {
            Optional<Long> saveResult = null;
            try {
                saveResult = bankRepository.saveBank(Optional.of(bank));
            } catch (Exception e) {
                transactionStatus.setRollbackOnly();
                e.printStackTrace();
            }
            return saveResult.get();
        });



      return  result;
    }



}
