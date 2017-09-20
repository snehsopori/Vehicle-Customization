package com.cdk.dmg.vehicle.customization.controller;

import com.cdk.dmg.vehicle.customization.model.Customer;
import com.cdk.dmg.vehicle.customization.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/customers",produces = APPLICATION_JSON_VALUE ,method = RequestMethod.GET)
    public Collection<Customer> readAllCustomersAsJson(){
        return customerService.readAll();
    }

    @RequestMapping(value = "/deleteCustomer/{id}",produces = TEXT_PLAIN_VALUE ,method = RequestMethod.DELETE)
    public String deleteCustomer(@PathVariable int id){
        customerService.remove(id);
        return "Customer with id '"+id+"'resource deleted successfully!";
    }

    @RequestMapping(value = "/updateCustomer/{id}",consumes = APPLICATION_JSON_VALUE ,produces = TEXT_PLAIN_VALUE ,method = RequestMethod.PUT)
    public String updateCustomer(@RequestBody Customer customer, @PathVariable int id){
        customer.setId(id);
        int value = customerService.modify(customer);
        return "Customer with id '"+value+" ' resource updated successfully!";
    }

    @RequestMapping(value = "/addCustomer",consumes = APPLICATION_JSON_VALUE ,produces = TEXT_PLAIN_VALUE ,method = RequestMethod.POST)
    public String addCustomer(@RequestBody Customer customer){
        System.out.println(customer);
        int value = customerService.add(customer);
        return value + "";
    }

    @RequestMapping(value = "/customer/{id}",produces = APPLICATION_JSON_VALUE ,method = RequestMethod.GET)
    public Customer readCustomerById(@PathVariable Integer id){
        System.out.println("ID is : "+id);
        Customer customer = customerService.readById(id);
        return customer;
    }
}