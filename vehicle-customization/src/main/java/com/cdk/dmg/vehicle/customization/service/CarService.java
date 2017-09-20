package com.cdk.dmg.vehicle.customization.service;

import com.cdk.dmg.vehicle.customization.dao.CarDAO;
import com.cdk.dmg.vehicle.customization.dao.CarRepository;
import com.cdk.dmg.vehicle.customization.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class CarService {

    @Autowired
    CarDAO carDAO;

    @Transactional
    public int add(Car car) {
        //20 loc
        return carDAO.save(car);
    }

    @Transactional
    public int modify(Car car) {
        return carDAO.update(car);
    }

    @Transactional
    public Car readByVin(Integer vin) {
        return carDAO.selectByVin(vin);
    }

    @Transactional
    public List<Car> readAll() {
        return carDAO.selectAll();
    }

    @Transactional
    public void remove(int vin) {
        carDAO.delete(vin);
    }

    public List<String> readSelectedCarByMake(String make) {
        return carDAO.selectByMake(make);
    }

    public List<String> readSelectedCarByModel(String make, String model) {
        return carDAO.selectByModel(make, model);
    }

    public List<Integer> readSelectedCarByTrim(String make, String model, String trim) {
        return carDAO.selectByTrim(make, model, trim);
    }

    @Autowired
    CarRepository repo;
    public Integer readSelectedCarByYear(String make, String model, String trim, int year) {
//        return carDAO.selectByYear(make, model, trim, year);
        Car car = repo.findByMakeAndModelAndCtrimAndYear(make, model, trim, year);
        return car == null ? 0 : car.getVin();
    }
}
