package com.sw2.onms.order;

import com.sw2.onms.customer.model.Customer;
import com.sw2.onms.product.model.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Order {

    @Getter
    @Setter
    private int orderID;
    @Getter(AccessLevel.NONE)
    @Setter
    private List<Product> products;
    @Getter
    @Setter
    private List<Order> components;
    @Getter
    @Setter
    private String address;
    @Getter
    @Setter
    private Customer customer;
    @Getter
    @Setter
    private OrderState orderState;

    public Order(){
        components = new ArrayList<>();
    }

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
            price+=product.getPrice()* product.getCount();
        }
        return price;
    }

    public void addComponent(Order component){
        components.add(component);
    }

    public List<String> getProductsNames(){
        List<String> productNames = new ArrayList<>();
        for (Product p : products) {
            productNames.add(p.getName());
        }
        return productNames;
    }

    public List<Long> getProductsSerialNumbers(){
        List<Long> productsSerialNumbers = new ArrayList<>();
        for (Product p: products) {
            productsSerialNumbers.add(p.getSerialNumber());
        }
        return productsSerialNumbers;
    }

}
