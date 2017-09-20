package com.cdk.dmg.vehicle.customization.dao;

import com.cdk.dmg.vehicle.customization.model.Accessories;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AccessoriesDAO {
    @PersistenceContext
    EntityManager entityManager;

    public int update(Accessories accessories) {
        entityManager.merge(accessories);
        return accessories.getAccId();
    }

    public int save(Accessories accessories) {
        entityManager.persist(accessories);
        return accessories.getAccId();
    }

    public List<Accessories> selectAll() {
        return entityManager.createQuery("from Accessories").getResultList();

    }

    public void delete(int id) {
        Accessories accessories = entityManager.find(Accessories.class, id);
        entityManager.remove(accessories);
    }

    public List<Accessories> selectById(int id) {
        return entityManager.createQuery("from Accessories where aid='"+id+"'").getResultList();
    }

    public Integer selectByAid(int id) {
        return (Integer)entityManager.createQuery("SELECT aid FROM accessories_detail where acc_id ='" + id +"'").getSingleResult();
    }

    public Accessories selectByAccid(Integer id) {
        return (Accessories) entityManager.createQuery("from Accessories where acc_id='"+id+"'").getSingleResult();
    }
}
