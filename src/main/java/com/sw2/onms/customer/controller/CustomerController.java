package com.sw2.onms.customer.controller;

import com.sw2.onms.customer.model.Customer;
import com.sw2.onms.customer.service.CustomersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    CustomersService customersService;

    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer){return customersService.addCustomer(customer);}
    @GetMapping("/get/{email}")
    public Customer getCustomer(@PathVariable String email){return customersService.getCustomer(email);}
    @GetMapping("/get/all")
    public List<Customer> getCustomers(){return customersService.getCustomers();}
    @PutMapping("/update/{email}")
    public Customer updateCustomer(@PathVariable String email, @RequestBody Customer customer){return customersService.updateCustomer(email, customer);}
    @DeleteMapping("/delete/{email}")
    public Customer deleteCustomer(@PathVariable String email){return customersService.deleteCustomer(email);}
}
