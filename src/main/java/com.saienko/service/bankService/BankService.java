package com.saienko.service.bankService;

import com.saienko.model.Bank;
import com.saienko.model.User;

import java.util.List;

/**
 * Created by gleb on 04.12.2015.
 */
public interface BankService {

    Bank findBankById(int bankId);
    Bank findBankByName(String BankName);
    void saveBank(Bank bank);
    void updateBank(Bank bank);
    void deleteBank(Integer bankId);
    List<Bank> findAllBanks();
    List<Bank> findAllUserBanks(User user);
    boolean isBankUnique(Integer bankId, String bankName);
}
