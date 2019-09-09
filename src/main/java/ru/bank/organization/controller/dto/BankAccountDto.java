package ru.bank.organization.controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@ToString
@Component
public class BankAccountDto implements BankAbstractionDto {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Integer currency;

    @Getter
    @Setter
    private Double amount;

    @Getter
    @Setter
    private Double amountOfCredit;


}
