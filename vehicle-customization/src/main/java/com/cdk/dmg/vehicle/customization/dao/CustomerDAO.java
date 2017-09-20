package com.cdk.dmg.vehicle.customization.dao;

import com.cdk.dmg.vehicle.customization.model.Customer;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomerDAO {
    @PersistenceContext
    EntityManager entityManager;

    public int update(Customer customer) {
        entityManager.merge(customer);
        return customer.getId();
    }

    public int save(Customer customer) {
        entityManager.persist(customer);
        return customer.getId();
    }

    public Customer selectById(Integer id) {
        return entityManager.find(Customer.class, id);
    }

    public List<Customer> selectAll() {
        return entityManager.createQuery("from Customer").getResultList();

    }

    public void delete(int id) {
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.remove(customer);
    }
}
