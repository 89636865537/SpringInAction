package ru.bank.organization.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bank.organization.controller.dto.BankAccountDto;
import ru.bank.organization.service.BankAccountService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/service")
public class BankAccountController {

    BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }


    @PostMapping(value = "/bankaccount")
    public Long saveBankAccount(@RequestBody final BankAccountDto bankAccountDto) {
        return bankAccountService.saveBankAccount(Optional.of(bankAccountDto));
    }

    @GetMapping(value = "/bankaccount", params = {"id"})
    public BankAccountDto getBankAccount(@RequestParam(name = "id") final String bankAccountId) {
        return bankAccountService.getBankAccount(Optional.of(bankAccountId));

    }

}
