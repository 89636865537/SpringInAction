package ru.bank.organization.controller.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.bank.organization.controller.dto.BankAbstractionDto;
import ru.bank.organization.controller.dto.BankAccountDto;
import ru.bank.organization.entity.BankAbstraction;
import ru.bank.organization.entity.BankAccount;

import java.util.Objects;
import java.util.Optional;

@Component(value = "bankAccountMapper")
public class BankAccountMapper implements BankAbstractMapper {

    @Autowired
    @Qualifier("mapper")
    ModelMapper mapper;


    @Override
    public BankAccount toEntity(BankAbstractionDto bankAbstractionDto) {


        BankAccountDto bankAccountDto = Optional.of(bankAbstractionDto).map((dto) -> {
            BankAccountDto resDto = null;
            if (dto instanceof BankAccountDto)
                resDto = (BankAccountDto) dto;
            return resDto;
        }).orElse(null);


        return Objects.nonNull(bankAccountDto) ? mapper.map(bankAccountDto, BankAccount.class) : null;
    }

    @Override
    public BankAccountDto toDto(BankAbstraction bankAbstraction) {
        BankAccount bankAccount = Optional.of(bankAbstraction).map((entity) -> {
            BankAccount resBank = null;
            if (entity instanceof BankAccount)
                resBank = (BankAccount) entity;
            return resBank;
        }).orElse(null);

        return Objects.nonNull(bankAccount) ? mapper.map(bankAccount, BankAccountDto.class) : null;
    }
}
