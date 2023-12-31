package com.sw2.onms.customer.service;

import com.sw2.onms.customer.model.Customer;
import com.sw2.onms.customer.repo.CustomersRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersService {
    private final CustomersRepo customersRepo;

    public CustomersService(CustomersRepo customersRepo) {
        this.customersRepo = customersRepo;
    }

    public Customer addCustomer(Customer customer) {
        return customersRepo.addCustomer(customer);
    }

    public Customer getCustomer(String email) {
        return customersRepo.get(email);
    }

    public List<Customer> getCustomers() {
        return customersRepo.getAll();
    }

    public Customer updateCustomer(String email, Customer customer) {
        return customersRepo.update(email, customer);
    }

    public Customer deleteCustomer(String email) {
        return customersRepo.remove(email);
    }

}
