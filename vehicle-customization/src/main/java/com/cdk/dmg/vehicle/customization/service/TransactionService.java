package com.cdk.dmg.vehicle.customization.service;

import com.cdk.dmg.vehicle.customization.dao.TransactionDAO;
import com.cdk.dmg.vehicle.customization.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionDAO transactionDAO;

    @Transactional
    public int add(Transaction transaction) {
        //20 loc
        return transactionDAO.save(transaction);
    }

    @Transactional
    public int modify(int id, int cid) {
        return transactionDAO.update(id, cid);
    }

    @Transactional
    public List<Transaction> readAll() {
        return transactionDAO.selectAll();
    }

    @Transactional
    public void remove(int id) {
        transactionDAO.delete(id);
    }

    public Transaction readTansaction(int id) {
        return transactionDAO.selectTransaction(id);
    }
}
