package com.cdk.dmg.vehicle.customization.controller;

import com.cdk.dmg.vehicle.customization.model.Car;
import com.cdk.dmg.vehicle.customization.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static org.springframework.http.MediaType.*;

@RestController
public class CarResourceController {

    @Autowired
    CarService carService;

    @RequestMapping(value = "/cars", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Collection<Car> readAllCarsAsJson() {
        return carService.readAll();
    }

    @RequestMapping(value = "/deleteCar/{vin}", produces = TEXT_PLAIN_VALUE, method = RequestMethod.DELETE)
    public String deleteCar(@PathVariable int vin) {
        carService.remove(vin);
        return "Car with vin '" + vin + "'resource deleted successfully!";
    }

    @RequestMapping(value = "/updateCar/{vin}", consumes = APPLICATION_JSON_VALUE, produces = TEXT_PLAIN_VALUE, method = RequestMethod.PUT)
    public String updateCar(@RequestBody Car car, @PathVariable int vin) {
        car.setVin(vin);
        int value = carService.modify(car);
        return "Car with vin '" + value + " ' resource updated successfully!";
    }


    @RequestMapping(value = "/addCar", consumes = APPLICATION_JSON_VALUE, produces = TEXT_PLAIN_VALUE, method = RequestMethod.POST)
    public String addCar(@RequestBody Car car) {
        System.out.println(car);
        int value = carService.add(car);
        return "Car with vin '" + value + " ' resource added successfully!";
    }

    @RequestMapping(value = "/car/{vin}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Car readCarByVin(@PathVariable Integer vin) {
        System.out.println("VIN is : " + vin);
        Car car = carService.readByVin(vin);
        return car;
    }

    @RequestMapping(value = "/car", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Car readCar() {
        Car car = carService.readByVin(1);
        return car;
    }

    @RequestMapping(value = "/cars/{make}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<String> readCarByMake(@PathVariable String make) {
        return carService.readSelectedCarByMake(make);
    }

    @RequestMapping(value = "/cars/{make}/{model}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<String> readCarByModel(@PathVariable String make, @PathVariable String model) {
        return carService.readSelectedCarByModel(make, model);
    }

    @RequestMapping(value = "/cars/{make}/{model}/{trim}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Integer> readCarByTrim(@PathVariable String make, @PathVariable String model, @PathVariable String trim) {
        return carService.readSelectedCarByTrim(make, model, trim);
    }

    @RequestMapping(value = "/cars/{make}/{model}/{trim}/{year}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Integer readCarByYear(@PathVariable String make, @PathVariable String model, @PathVariable String trim, @PathVariable int year) {
        return carService.readSelectedCarByYear(make, model, trim, year);
    }
}
