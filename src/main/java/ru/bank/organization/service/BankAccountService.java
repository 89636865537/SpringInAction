package ru.bank.organization.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.bank.organization.controller.dto.BankAccountDto;
import ru.bank.organization.controller.mapper.BankAbstractMapper;
import ru.bank.organization.entity.BankAccount;
import ru.bank.organization.repository.BankAccountRepository;

import java.util.Optional;

@Service
public class BankAccountService {

    private static final Logger log = LoggerFactory.getLogger(BankAccountService.class);
    BankAccountRepository bankAccountRepository;

    BankAbstractMapper bankAbstractMapper;

    @Autowired
    public BankAccountService(@Qualifier("hibernateBankAccount") BankAccountRepository bankAccountRepository,
                              @Qualifier("bankAccountMapper") BankAbstractMapper bankAbstractMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankAbstractMapper = bankAbstractMapper;
    }

    public BankAccountDto getBankAccount(Optional<String> bankIdOpt) {
        Long bankId = bankIdOpt.map(Long::parseLong).orElseThrow(() -> {
            log.warn("input bankAccount id is null");
            return new IllegalArgumentException();
        });
        BankAccount bankAccount = bankAccountRepository.getBankAccountById(bankId);
        BankAccountDto bankAccountDto = (BankAccountDto) bankAbstractMapper.toDto(bankAccount);

        return bankAccountDto;
    }

    @Transactional(propagation = Propagation.REQUIRED, timeout = 5000, isolation = Isolation.READ_COMMITTED, transactionManager = "hibernateTransactionManager", noRollbackFor = {IllegalArgumentException.class},  rollbackFor = {Exception.class})
    public Long saveBankAccount(Optional<BankAccountDto> bankAccountDtoOptional) {
        BankAccountDto bankAccountDto = bankAccountDtoOptional.orElseThrow(() -> {
            log.warn("bankAccount is null");
            return new IllegalArgumentException();
        });
        BankAccount bankAccount = (BankAccount) bankAbstractMapper.toEntity(bankAccountDto);
        return bankAccountRepository.saveBankAccount(bankAccount);
    }
}
