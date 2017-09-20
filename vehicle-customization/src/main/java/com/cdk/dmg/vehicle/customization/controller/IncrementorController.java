package com.cdk.dmg.vehicle.customization.controller;


import com.cdk.dmg.vehicle.customization.model.Incrementor;
import com.cdk.dmg.vehicle.customization.model.Transaction;
import com.cdk.dmg.vehicle.customization.service.IncrementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
public class IncrementorController {

    @Autowired
    IncrementService incrementService;

    @RequestMapping(value = "/increments", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Collection<Incrementor> readAllIncrements() {
        return incrementService.readAll();
    }

    @RequestMapping(value = "/updateIncrementor/{id}", consumes = APPLICATION_JSON_VALUE, produces = TEXT_PLAIN_VALUE, method = RequestMethod.PUT)
    public String updateIncrementor(@RequestBody Incrementor incrementor, @PathVariable int id) {
        incrementor.setIncrement(id);
        int value = incrementService.modify(incrementor);
        return "Incrementor with id '" + value + " ' resource updated successfully!";
    }


    @RequestMapping(value = "/addIncrementor", consumes = APPLICATION_JSON_VALUE, produces = TEXT_PLAIN_VALUE, method = RequestMethod.POST)
    public String addIncrementor(@RequestBody Incrementor incrementor) {
        System.out.println(incrementor);
        int value = incrementService.add(incrementor);
        return "Incrementor with id '" + value + " ' resource added successfully!";
    }

    @RequestMapping(value = "/incrementor/{tid}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Integer> readIncrement(@PathVariable int tid) {
        return incrementService.readIncrement(tid);
    }
}
