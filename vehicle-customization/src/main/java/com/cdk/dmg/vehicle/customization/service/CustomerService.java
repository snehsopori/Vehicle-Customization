package com.cdk.dmg.vehicle.customization.service;

import com.cdk.dmg.vehicle.customization.dao.CustomerDAO;
import com.cdk.dmg.vehicle.customization.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerDAO customerDAO;

    @Transactional
    public int add(Customer customer) {
        //20 loc
        return customerDAO.save(customer);
    }

    @Transactional
    public int modify(Customer customer) {
        return customerDAO.update(customer);
    }

    @Transactional
    public Customer readById(Integer id) {
        return customerDAO.selectById(id);
    }

    @Transactional
    public List<Customer> readAll() {
        return customerDAO.selectAll();

    }

    @Transactional
    public void remove(int id) {
        customerDAO.delete(id);
    }
}
