package com.saienko.service.bankService;

import com.saienko.dao.bankDao.BankDao;
import com.saienko.model.Bank;
import com.saienko.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by gleb on 04.12.2015.
 */
@Transactional
@Service("bankService")
public class BankServiceImpl implements BankService {

    @Autowired
    private BankDao bankDao;


    public Bank findBankById(int bankId) {
        return bankDao.findBankById(bankId);
    }


    public Bank findBankByName(String bankName) {
        return bankDao.findBankByName(bankName);
    }


    public void saveBank(Bank bank) {
        bankDao.saveBank(bank);

    }


    public void updateBank(Bank bank) {
        Bank entity = bankDao.findBankById(bank.getBankId());
        if (entity != null){
        entity.setBankName(bank.getBankName());
        }
    }


    public void deleteBank(Integer bankId) {
        bankDao.deleteBank(bankId);
    }


    public List<Bank> findAllBanks() {
        return bankDao.findAllBanks();
    }


    public List<Bank> findAllUserBanks(User user) {
        return bankDao.findAllUserBanks(user);
    }


    public boolean isBankUnique(Integer bankId, String bankName) {
        Bank bank = findBankByName(bankName);
        return (bank == null || (bankId != null) && (bank.getBankId() == bankId));
    }
}
