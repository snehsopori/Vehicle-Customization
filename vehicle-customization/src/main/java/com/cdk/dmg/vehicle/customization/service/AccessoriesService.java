package com.cdk.dmg.vehicle.customization.service;

import com.cdk.dmg.vehicle.customization.dao.AccessoriesDAO;
import com.cdk.dmg.vehicle.customization.model.Accessories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class AccessoriesService {

    @Autowired
    AccessoriesDAO accessoriesDAO;

    @Transactional
    public int add(Accessories accessories) {
        //20 loc
        return accessoriesDAO.save(accessories);
    }

    @Transactional
    public int modify(Accessories accessories) {
        return accessoriesDAO.update(accessories);
    }

    @Transactional
    public List<Accessories> readAll() {
        return accessoriesDAO.selectAll();
    }

    @Transactional
    public void remove(int id) {
        accessoriesDAO.delete(id);
    }

    public List<Accessories> readById(int id) {
        return accessoriesDAO.selectById(id);
    }

    public Integer readByAid(int id) {
        return accessoriesDAO.selectByAid(id);
    }

    public Accessories readByAccid(Integer id) {
        return accessoriesDAO.selectByAccid(id);
    }
}
