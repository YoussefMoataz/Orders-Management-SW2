package com.sw2.onms.order;

import com.sw2.onms.NotificationManagement.NotificationService.NotificationService;
import com.sw2.onms.customer.model.Customer;
import com.sw2.onms.product.model.Product;
import com.sw2.onms.product.repo.ProductsRepo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Youssef Moataz
 * @author Hassan Magdi
 */
@Getter
@Setter
public class Order {

    @Getter
    @Setter
    private int orderID;
    @Getter(AccessLevel.PRIVATE)
    private List<Product> products;
    @Getter(AccessLevel.PUBLIC)
    private Map<Long, Integer> productsCount;
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
    @Getter
    @Setter
    private Long shippingTime;

    public Order() {
        components = new ArrayList<>();
        products = new ArrayList<>();
        productsCount = new HashMap<>();
    }

    public Order(List<Product> products, List<Order> components, String address, Customer customer) {
        this.products = products;
        this.components = components;
        this.address = address;
        this.customer = customer;
    }

    public Order listDetails() {
        return this;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public double getPrice() {
        double price = 0;
        for (Product product : products) {
            price += product.getPrice() * product.getCount();
        }
        return price;
    }

    public void addComponent(Order component) {
        components.add(component);
    }

    /**
     * Used in {@link NotificationService}
     *
     * @return List of product names
     */
    public List<String> getProductsNames() {
        List<String> productNames = new ArrayList<>();
        for (Product p : products) {
            productNames.add(p.getName());
        }
        return productNames;
    }

    public List<Long> getProductsSerialNumbers() {
        List<Long> productsSerialNumbers = new ArrayList<>();
        for (Product p : products) {
            productsSerialNumbers.add(p.getSerialNumber());
        }
        return productsSerialNumbers;
    }

    public Integer getCount(Long serial) {
        for (Product p : products) {
            if (p != null) {
                if (p.getSerialNumber() == serial) {
                    return p.getCount();
                }
            }
        }
        return 0;
    }

    public void setProducts(Map<Long, Integer> productsCount) {
        ProductsRepo productsRepo = new ProductsRepo();
        List<Product> productsList = new ArrayList<>();
        for (Map.Entry<Long, Integer> pair : productsCount.entrySet()) {
            Product product = productsRepo.getBySerialNumber(pair.getKey());
            product.setCount(pair.getValue());
            productsList.add(product);
        }
        products = productsList;
    }

}
