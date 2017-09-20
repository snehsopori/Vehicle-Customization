package com.cdk.dmg.vehicle.customization.dao;

import com.cdk.dmg.vehicle.customization.model.Incrementor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class IncrementorDAO {
    @PersistenceContext
    EntityManager entityManager;

    public int update(Incrementor incrementor) {
        entityManager.merge(incrementor);
        return incrementor.getIncrement();
    }

    public int save(Incrementor incrementor) {
        entityManager.persist(incrementor);
        return incrementor.getIncrement();
    }

    public List selectAll() {
        return entityManager.createQuery("from Incrementor").getResultList();

    }

    public void delete(int id) {
        Incrementor incrementor = entityManager.find(Incrementor.class, id);
        entityManager.remove(incrementor);
    }

    public List<Integer> selectIncrementor(int tid) {
        return entityManager.createQuery("select aid from Incrementor where tid='" + tid + "'").getResultList();
    }
}
