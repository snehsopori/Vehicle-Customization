package com.cdk.dmg.vehicle.customization.dao;

import com.cdk.dmg.vehicle.customization.model.Accessories;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

public class AccessoryDAOTest {
    @Mock
    EntityManager entityManager;

    @InjectMocks
    AccessoriesDAO accessoriesDAO;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testUpdate(){
        Accessories accessories=Mockito.mock(Accessories.class);
        accessories.setAccessoryName("BMW_FootMat");
        accessories.setAccessoryPrice(673);
        accessories.setAccId(23);
        accessories.setImage("jh.jpg");
        entityManager.persist(accessories);
        accessories.setAccId(46);
        Mockito.when(accessories.getAccId()).thenReturn(46);
        entityManager.merge(accessories);
        Assert.assertEquals(46,accessories.getAccId());
    }

    @Test
    public void testSave() {
        Accessories accessories=Mockito.mock(Accessories.class);
        accessories.setAccessoryName("BMW_FootMat");
        accessories.setAccessoryPrice(673);
        accessories.setAccId(23);
        accessories.setImage("jh.jpg");
        Mockito.when(accessories.getAccId()).thenReturn(23);
        entityManager.persist(accessories);
        Assert.assertEquals(23,accessories.getAccId());
    }

    @Test
    public void testSelectById(){
        Accessories accessories = Mockito.mock(Accessories.class);
        Mockito.when(entityManager.find(Accessories.class, 1)).thenReturn(accessories);
        accessoriesDAO.selectById(1);
        Mockito.verify(entityManager).find(Accessories.class,1);
    }

    @Test
    public void testSelectAll(){
        Query query = Mockito.mock(Query.class);
        Accessories accessories=Mockito.mock(Accessories.class);
        Mockito.when(query.getResultList()).thenReturn(Arrays.asList(1));
        Mockito.when(entityManager.createQuery("from Accessories")).thenReturn(query);
        accessoriesDAO.save(new Accessories("BMW_Footmats",545,"foot.jpg"));
        List<Accessories> list = accessoriesDAO.selectAll();
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testDelete(){
        Accessories accessories = Mockito.mock(Accessories.class);
        Mockito.when(entityManager.find(Accessories.class, 123)).thenReturn(accessories);
        accessoriesDAO.delete(123);
        Mockito.verify(entityManager).remove(accessories);
    }

}