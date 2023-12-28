package com.sw2.onms.customer.controller;

import com.sw2.onms.customer.model.Customer;
import com.sw2.onms.customer.service.CustomersService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    CustomersService customersService;

    public Customer addCustomer(Customer customer){return customersService.addCustomer(customer);}
    public Customer getCustomer(String email){return customersService.getCustomer(email);}
    public List<Customer> getCustomers(){return customersService.getCustomers();}
    public Customer updateCustomer(String email, Customer customer){return customersService.updateCustomer(email, customer);}
    public Customer deleteCustomer(String email){return customersService.deleteCustomer(email);}
}
