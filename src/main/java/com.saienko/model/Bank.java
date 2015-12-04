package com.saienko.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by gleb on 04.12.2015.
 */
@Entity
@Table(name="BANK")
public class Bank {

    @Id
    @NotNull
    @Column(name="BANK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bankId;

    @NotNull
    @Column(name = "BANK_NAME")
    private String bankName;

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

}
