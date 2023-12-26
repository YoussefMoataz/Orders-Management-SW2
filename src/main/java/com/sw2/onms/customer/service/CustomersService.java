package com.sw2.onms.customer.service;

import com.sw2.onms.customer.model.Customer;
import com.sw2.onms.customer.repo.CustomersRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersService {
    private final CustomersRepo customersRepo;
    CustomersService(CustomersRepo customersRepo){
        this.customersRepo = customersRepo;
    }
    public Customer addCustomer(Customer customer){return null;}
    public Customer getCustomer(String email){return null;}
    public List<Customer> getCustomers(){return null;}
    public boolean updateCustomer(String email, Customer customer){return false;}
    public boolean deleteCustomer(Customer customer){return false;}

}
