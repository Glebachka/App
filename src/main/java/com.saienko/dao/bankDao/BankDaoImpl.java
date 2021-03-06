package com.saienko.dao.bankDao;

import com.saienko.dao.AbstractDao;
import com.saienko.model.Bank;
import com.saienko.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gleb on 04.12.2015.
 */
@Repository
public class BankDaoImpl extends AbstractDao<Integer, Bank> implements BankDao {


    public void saveBank(Bank bank) {
        persist(bank);
    }


    public void deleteBank(Integer bankId) {
        Query query = getSession().createSQLQuery("DELETE FROM bank WHERE BANK_ID = :bankId ");
        query.setInteger("bankId", bankId);
        query.executeUpdate();
    }


    public Bank findBankById(Integer bankId) {
       return getByKey(bankId);
    }


    public Bank findBankByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("bankName", name));
        return (Bank) criteria.uniqueResult();
    }

    public List<Bank> findAllBanks() {
        Criteria criteria = createEntityCriteria();
        return (List<Bank>) criteria.list();
    }

    public List<Bank> findAllUserBanks(User user) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("user", user));
        return (List<Bank>) criteria.list();
    }
}
