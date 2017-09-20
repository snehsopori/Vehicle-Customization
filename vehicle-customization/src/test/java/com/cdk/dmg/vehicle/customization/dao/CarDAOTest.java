package com.cdk.dmg.vehicle.customization.dao;

import com.cdk.dmg.vehicle.customization.model.Accessories;
import com.cdk.dmg.vehicle.customization.model.Car;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarDAOTest {
    @Mock
    EntityManager entityManager;

    @InjectMocks
    CarDAO carDAO;


    @Before


    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
     @Test
     public void testSave(){
         Car car=Mockito.mock(Car.class);
         car.setVin(12);
         Mockito.when(car.getVin()).thenReturn(12);
         entityManager.persist(car);
         Assert.assertEquals(12,car.getVin());
     }

     @Test
     public void testUpdate(){
         Car car=Mockito.mock(Car.class);
         car.setVin(12);
         entityManager.persist(car);
         Mockito.when(car.getVin()).thenReturn(22);
         car.setVin(22);
         entityManager.merge(car);
         Assert.assertEquals(22,car.getVin());

     }

    @Test
    public void testSelectByVin(){
        Car car = Mockito.mock(Car.class);
        Mockito.when(entityManager.find(Car.class, 1)).thenReturn(car);
        carDAO.selectByVin(1);
        Mockito.verify(entityManager).find(Car.class,1);
    }

    @Test


    public void testDelete() {
        Car car = Mockito.mock(Car.class);
        Mockito.when(entityManager.find(Car.class, 123)).thenReturn(car);
        carDAO.delete(123);
        Mockito.verify(entityManager).remove(car);
    }


    @Test
    public void testSelectByMake() {
        Query query = Mockito.mock(Query.class);
        Mockito.when(query.getResultList()).thenReturn(Arrays.asList(1));
        Mockito.when(entityManager.createQuery("select distinct model from Car where make ='BMW'")).thenReturn(query);
        List<String> list = carDAO.selectByMake("BMW");
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testSelectByModel() {
        Query query = Mockito.mock(Query.class);
        Mockito.when(query.getResultList()).thenReturn(Arrays.asList(1));
        Mockito.when(entityManager.createQuery("select distinct ctrim from Car where make ='BMW' and " + "model ='3 Series'")).thenReturn(query);
        List<String> list = carDAO.selectByModel("BMW", "3 Series");
        Assert.assertEquals(1, list.size());
    }

    @Test

    public void testSelectByTrim() {
        Query query = Mockito.mock(Query.class);
        Mockito.when(query.getResultList()).thenReturn(Arrays.asList(1));
        Mockito.when(entityManager.createQuery("select distinct year from Car where make ='BMW' and " + "model ='3 Series' and " + "ctrim ='trim'")).thenReturn(query);
        List<Integer> list = carDAO.selectByTrim("BMW", "3 Series", "trim");
        Assert.assertEquals(1, list.size());
    }
    @Test
    public  void testSelectAll(){
        Query query = Mockito.mock(Query.class);
        Car car=Mockito.mock(Car.class);
        Accessories accessories=Mockito.mock(Accessories.class);
        accessories.setAccessoryName("BMW_FootMat");
        accessories.setAccessoryPrice(673);
        accessories.setAccId(23);
        accessories.setImage("jh.jpg");
        Set<Accessories> set=new HashSet<>();
        set.add(accessories);
        Mockito.when(query.getResultList()).thenReturn(Arrays.asList(1));
        Mockito.when(entityManager.createQuery("from Car")).thenReturn(query);//08030636444
        carDAO.save(new Car(1, "BMW", "Z8", 2016, "LX",set));
        List<Car> list = carDAO.selectAll();
        Assert.assertEquals(1, list.size());
    }
}