package ru.bank.organization.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bank.organization.entity.Bank;
import ru.bank.organization.service.BankService;

@RestController
@RequestMapping(value = "/service")
public class BankController {

    @Autowired
    BankService bankService;

    @GetMapping(value = "/bank", params = {"id"})
    public Bank getBank(@RequestParam(value = "id") String bankId) {

        return bankService.getBank(bankId);
    }

    @PostMapping(value = "/bank")

    public Long saveBank(@RequestBody Bank bank) {

        return bankService.saveBank(bank);
    }

}
