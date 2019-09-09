package ru.bank.organization.controller.mapper;

import org.springframework.stereotype.Component;
import ru.bank.organization.controller.dto.BankAbstractionDto;
import ru.bank.organization.entity.BankAbstraction;

@Component
public interface BankAbstractMapper {

    BankAbstraction toEntity(BankAbstractionDto bankAbstraction);

    BankAbstractionDto toDto(BankAbstraction bankAbstraction);
}
