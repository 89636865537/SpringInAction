package ru.bank.organization.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.bank.organization.entity.Bank;
import ru.bank.organization.service.BankService;

@Controller
@RequestMapping("/mvc")
public class BankMvcController {

    BankService bankService;

    @Autowired
    public BankMvcController(BankService bankService){
        this.bankService =  bankService;
    }

    @GetMapping(value = "/bank", params = {"id"})
    public String getBank(@RequestParam(value = "id") String bankId) {
        Bank bank = bankService.getBank(bankId);
        System.out.println("in mvc controller");
        return "bank";
    }


}
