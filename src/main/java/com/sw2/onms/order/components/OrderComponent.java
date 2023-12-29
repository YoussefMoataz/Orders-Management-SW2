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

    public abstract OrderComponent listDetails();

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

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getOrderID(){
        return orderID;
    }

    public void setOrderState(OrderState state){
        this.orderState = state;
    }

}
