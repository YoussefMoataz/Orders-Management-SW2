package com.sw2.onms.customer.repo;

import com.sw2.onms.customer.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class CustomersRepo {
    private final HashMap<String, Customer> customers;

    CustomersRepo(){
        customers = new HashMap<>();
    }
    public Customer addCustomer(Customer customer){
        customers.put(customer.getEmail(), customer);
        return customers.get(customer.getEmail());
    }

    public Customer get(String email){
        return customers.get(email);
    }

    public List<Customer> getAll(){
        ArrayList<Customer> allCustomers = new ArrayList<>();
        allCustomers.addAll(customers.values());
        return allCustomers;
    }

    public Customer update(String email, Customer customer){
        customers.put(email, customer);
        return customers.get(email);
    }

    public Customer remove(String email){
        return customers.remove(email);
    }
}
