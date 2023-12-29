package com.sw2.onms.order;

import com.sw2.onms.customer.model.Customer;
import com.sw2.onms.product.model.Product;
import java.util.List;

public class Order {

    private int orderID;
    private List<Product> products;
    private List<Order> components;
    private String address;
    private Customer customer;
    private OrderState orderState;

    public Order(List<Product> products, List<Order> components, String address, Customer customer){
        this.products = products;
        this.components = components;
        this.address = address;
        this.customer = customer;
    }

    public Order listDetails() {
        return this;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProduct(Product product){
        products.remove(product);
    }

    public double getPrice(){
        double price=0;
        for(Product product:products){
            price+=product.getPrice();
        }
        return price;
    }

    public void addComponent(Order component){
        components.add(component);
    }

    public List<Order> getComponents() {
        return components;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getOrderID(){
        return orderID;
    }

    public void setOrderState(OrderState state){
        this.orderState = state;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
