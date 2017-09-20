package com.cdk.dmg.vehicle.customization.dao;

import com.cdk.dmg.vehicle.customization.model.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerDAOTest {
    @Mock
    EntityManager entityManager;

    @InjectMocks
    CustomerDAO customerDAO;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdate() {
        Customer customer=Mockito.mock(Customer.class);
        customer.setId(12);
        customer.setCity("Pune");
        customer.setEmail("AP@gmail.com");
        customer.setName("User");
        customer.setPhone("87897979");
        entityManager.persist(customer);
        customer.setId(67);
        Mockito.when(customer.getId()).thenReturn(67);
        entityManager.merge(customer);
        Assert.assertEquals(67,customer.getId());
    }

    @Test
    public void testSave() {
        Customer customer=Mockito.mock(Customer.class);
        customer.setId(12);
        customer.setCity("Pune");
        customer.setEmail("AP@gmail.com");
        customer.setName("User");
        customer.setPhone("87897979");
        Mockito.when(customer.getId()).thenReturn(12);
        entityManager.persist(customer);
        Assert.assertEquals(12,customer.getId());
    }

    @Test
    public void testSelectById() {
        Customer customer = Mockito.mock(Customer.class);
        Mockito.when(entityManager.find(Customer.class, 1)).thenReturn(customer);
        customerDAO.selectById(1);
        Mockito.verify(entityManager).find(Customer.class,1);
    }

    @Test
    public void testSelectAll() {
        Query query = Mockito.mock(Query.class);
        Customer accessories=Mockito.mock(Customer.class);
        Mockito.when(query.getResultList()).thenReturn(Arrays.asList(1));
        Mockito.when(entityManager.createQuery("from Customer")).thenReturn(query);
        customerDAO.save(new Customer(10, "User","user@gmail.com","878788980","Pune"));
        List<Customer> list =customerDAO.selectAll();
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testDelete() {
        Customer customer = Mockito.mock(Customer.class);
        Mockito.when(entityManager.find(Customer.class, 123)).thenReturn(customer);
        customerDAO.delete(123);
        Mockito.verify(entityManager).remove(customer);
    }

}