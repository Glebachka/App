package com.saienko.dao.bankDao;

import com.saienko.dao.AbstractDao;
import com.saienko.model.Bank;
import com.saienko.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gleb on 04.12.2015.
 */
@Repository
public class BankDaoImpl extends AbstractDao<Integer, Bank> implements BankDao {
    @Override
    public void saveBank(Bank bank) {
        persist(bank);
    }

    @Override
    public void deleteBank(Bank bank) {
        delete(bank);
    }

    @Override
    public void findBankById(Integer bank_id) {

    }

    @Override
    public List<Bank> findAllBanks() {
        return null;
    }

    @Override
    public List<Bank> findAllUserBanks(User user) {
        return null;
    }
}
