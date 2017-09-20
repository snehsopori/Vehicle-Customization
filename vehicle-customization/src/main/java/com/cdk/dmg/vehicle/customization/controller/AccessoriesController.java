package com.cdk.dmg.vehicle.customization.controller;


import com.cdk.dmg.vehicle.customization.model.Accessories;
import com.cdk.dmg.vehicle.customization.service.AccessoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
public class AccessoriesController {

    @Autowired
    AccessoriesService accessoriesService;

    @RequestMapping(value = "/accessories", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Collection<Accessories> readAllAccessories() {
        return accessoriesService.readAll();
    }

    @RequestMapping(value = "/deleteAccessory/{id}", produces = TEXT_PLAIN_VALUE, method = RequestMethod.DELETE)
    public String deleteAccessory(@PathVariable int id) {
        accessoriesService.remove(id);
        return "Accessory with id '" + id + "'resource deleted successfully!";
    }

    @RequestMapping(value = "/updateAccessory/{id}", consumes = APPLICATION_JSON_VALUE, produces = TEXT_PLAIN_VALUE, method = RequestMethod.PUT)
    public String updateAccessory(@RequestBody Accessories accessories, @PathVariable int id) {
        accessories.setAccId(id);
        int value = accessoriesService.modify(accessories);
        return "Accessory with id '" + value + " ' resource updated successfully!";
    }


    @RequestMapping(value = "/addAccessory", consumes = APPLICATION_JSON_VALUE, produces = TEXT_PLAIN_VALUE, method = RequestMethod.POST)
    public String addAccessory(@RequestBody Accessories accessories) {
        System.out.println(accessories);
        int value = accessoriesService.add(accessories);
        return "Accessory with id '" + value + " ' resource added successfully!";
    }

    @RequestMapping(value = "/accessories/{id}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Accessories> readAccessoryById(@PathVariable Integer id) {
        return accessoriesService.readById(id);
    }

    @RequestMapping(value = "/accessory/{id}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Integer readAccessoryByAid(@PathVariable Integer id) {
        return accessoriesService.readByAid(id);
    }

    @RequestMapping(value = "/acc/{id}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Accessories readAccessoryByAccId(@PathVariable Integer id) {
        return accessoriesService.readByAccid(id);
    }
}
