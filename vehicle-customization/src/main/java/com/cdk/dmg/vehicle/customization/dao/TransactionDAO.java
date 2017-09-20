package com.cdk.dmg.vehicle.customization.dao;

import com.cdk.dmg.vehicle.customization.model.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TransactionDAO {
    @PersistenceContext
    EntityManager entityManager;

    public int update(int id, int cid) {
        Transaction transaction = entityManager.find(Transaction.class, id);
        transaction.setCid(cid);
        return transaction.getTid();
    }

    public int save(Transaction transaction) {
        entityManager.persist(transaction);
        return transaction.getTid();
    }

    public List<Transaction> selectAll() {
        return entityManager.createQuery("from Transaction").getResultList();

    }

    public void delete(int id) {
        Transaction transaction = entityManager.find(Transaction.class, id);
        entityManager.remove(transaction);
    }


    public Transaction selectTransaction(int id) {
        Transaction transaction = entityManager.find(Transaction.class, id);
        return transaction;
    }
}
