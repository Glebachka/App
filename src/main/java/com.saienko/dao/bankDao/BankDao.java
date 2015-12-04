package com.saienko.dao.bankDao;

import com.saienko.model.Bank;
import com.saienko.model.User;

import java.util.List;

/**
 * Created by gleb on 04.12.2015.
 */
public interface BankDao {
    void  saveBank(Bank bank);
    void deleteBank(Integer bankId);
    Bank findBankById(Integer bankId);
    Bank findBankByName(String name);
    List<Bank> findAllBanks();
    List<Bank> findAllUserBanks(User user);
}
