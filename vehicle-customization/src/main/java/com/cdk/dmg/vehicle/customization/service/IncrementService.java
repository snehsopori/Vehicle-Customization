package com.cdk.dmg.vehicle.customization.service;

import com.cdk.dmg.vehicle.customization.dao.IncrementorDAO;
import com.cdk.dmg.vehicle.customization.model.Incrementor;
import com.cdk.dmg.vehicle.customization.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IncrementService {

    @Autowired
    IncrementorDAO incrementorDAO;

    @Transactional
    public int add(Incrementor incrementor) {
        //20 loc
        return incrementorDAO.save(incrementor);
    }

    @Transactional
    public int modify(Incrementor incrementor) {
        return incrementorDAO.update(incrementor);
    }

    @Transactional
    public List<Incrementor> readAll() {
        return incrementorDAO.selectAll();
    }

    @Transactional
    public void remove(int id) {
        incrementorDAO.delete(id);
    }

    public List<Integer> readIncrement(int tid) {
        return incrementorDAO.selectIncrementor(tid);
    }
}
