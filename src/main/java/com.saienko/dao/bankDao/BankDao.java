package com.saienko.dao.bankDao;

import com.saienko.model.Bank;
import com.saienko.model.User;

import java.util.List;

/**
 * Created by gleb on 04.12.2015.
 */
public interface BankDao {
    void  saveBank(Bank bank);
    void deleteBank(Bank bank);
    void findBankById(Integer bank_id);
    List<Bank> findAllBanks();
    List<Bank> findAllUserBanks(User user);
}
