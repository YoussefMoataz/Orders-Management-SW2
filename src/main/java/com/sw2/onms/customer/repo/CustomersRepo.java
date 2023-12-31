package com.sw2.onms.customer.repo;

import com.sw2.onms.NotificationManagement.NotificationService.NotificationSenderType;
import com.sw2.onms.NotificationManagement.NotificationService.TemplateCreation.Language;
import com.sw2.onms.customer.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class CustomersRepo {
    private final HashMap<String, Customer> customers;

    public CustomersRepo() {
        customers = new HashMap<>();

        // dummy customers for testing purpose
        customers.put("maged@gmail.com", new Customer("mohamed maged", "maged@gmail.com", "pass", 100.0, "01028746860"));
        customers.put("hassan@gmail.com", new Customer("hassan magdi", "hassan@gmail.com", "password", 170.0, "01011993990", Language.English));
        customers.put("youssef@gmail.com", new Customer("youssef moataz", "youssef@gmail.com", "pass123", 200.0, "01033148446", NotificationSenderType.Email));
        customers.put("sama@gmail.com", new Customer("sama ahmad", "sama@gmail.com", "p@ss1234", 195.0, "01142962772", Language.English, NotificationSenderType.SMS));
    }

    public Customer addCustomer(Customer customer) {
        customers.put(customer.getEmail(), customer);
        return customers.get(customer.getEmail());
    }

    public Customer get(String email) {
        return customers.get(email);
    }

    public List<Customer> getAll() {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        allCustomers.addAll(customers.values());
        return allCustomers;
    }

    public Customer update(String email, Customer customer) {
        customers.put(email, customer);
        return customers.get(email);
    }

    public Customer remove(String email) {
        return customers.remove(email);
    }
}
