package com.sw2.onms.order.components;

import com.sw2.onms.customer.model.Customer;
import com.sw2.onms.order.OrderState;
import com.sw2.onms.product.model.Product;

import java.util.List;

public abstract class OrderComponent {
    private int orderID;
    private List<Product> products;
    private String address;
    private Customer customer;
    private OrderState orderState;

    public abstract void listDetails();
    public abstract void addProduct(Product product);
    public abstract void removeProduct(Product product);
    public abstract double getPrice();
}
