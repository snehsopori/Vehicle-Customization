package com.cdk.dmg.vehicle.customization.dao;

import com.cdk.dmg.vehicle.customization.model.Car;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CarDAO {

    @PersistenceContext
    EntityManager entityManager;

    public int update(Car car) {
        entityManager.merge(car);
        return car.getVin();
    }

    public int save(Car car) {
        entityManager.persist(car);
        return car.getVin();
    }

    public Car selectByVin(Integer vin) {
        return entityManager.find(Car.class, vin);
    }

    public List<Car> selectAll() {
        return entityManager.createQuery("from Car").getResultList();

    }

    public void delete(int vin) {
        Car car = entityManager.find(Car.class, vin);
        entityManager.remove(car);
    }

    public List<String> selectByMake(String make) {
        System.out.println(make);
        return entityManager.createQuery("select distinct model from Car where make ='" + make + "'").getResultList();
    }

    public List<String> selectByModel(String make, String model) {
        System.out.println(make + model);
        return entityManager.createQuery("select distinct ctrim from Car where make ='" + make + "' and " + "model ='" + model + "'").getResultList();
    }

    public List<Integer> selectByTrim(String make, String model, String trim) {
        System.out.println(make + model + trim);
        return entityManager.createQuery("select distinct year from Car where make ='" + make + "' and " + "model ='" + model + "' and " + "ctrim ='" + trim + "'").getResultList();
    }

//    public Integer  selectByYear(String make, String model, String trim, int year) {
//        return entityManager.createQuery("select vin from Car where make ='" + make + "' and " + "model ='" + model + "' and " + "ctrim ='" + trim + "' and " + "year ='" + year + "'").getFirstResult();
//    }
}
