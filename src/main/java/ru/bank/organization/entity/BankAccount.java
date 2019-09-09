package ru.bank.organization.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Entity
@Table(name = "BANK_ACCOUNT")
public class BankAccount implements BankAbstraction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column(name = "AMOUNT_OF_CREDIT")
    private Double amountOfCredit;


}
