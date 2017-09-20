package com.cdk.dmg.vehicle.customization.controller;


import com.cdk.dmg.vehicle.customization.model.Transaction;
import com.cdk.dmg.vehicle.customization.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @RequestMapping(value = "/transactions", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Collection<Transaction> readAllTransaction() {
        return transactionService.readAll();
    }

    @RequestMapping(value = "/updateTransaction/{id}/{cid}", consumes = APPLICATION_JSON_VALUE, produces = TEXT_PLAIN_VALUE, method = RequestMethod.PUT)
    public String updateTransaction(@PathVariable int id, @PathVariable int cid) {
        int value = transactionService.modify(id, cid);
        return value + "";
    }


    @RequestMapping(value = "/addTransaction", consumes = APPLICATION_JSON_VALUE, produces = TEXT_PLAIN_VALUE, method = RequestMethod.POST)
    public String addTransaction(@RequestBody Transaction transaction) {
        System.out.println(transaction);
        int value = transactionService.add(transaction);
        return value+"";
    }

    @RequestMapping(value = "/transactionDetails/{id}", produces = APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Transaction readTransaction(@PathVariable int id) {
        return transactionService.readTansaction(id);
    }

}
