package com.sw2.onms.order;

import com.sw2.onms.product.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository {

    private static OrderRepository orderRepository;
    private static Integer orderID = 1;
    Map<Integer, Order> orders;

    private OrderRepository(){
        orders = new HashMap<>();
    }

    public static OrderRepository getInstance(){
        if (orderRepository == null){
            orderRepository = new OrderRepository();
        }
        return orderRepository;
    }

    int addOrder(Order order){
        order.setOrderID(orderID);
        orders.put(orderID,order);
        for (Order o: order.getComponents()) {
            o.setOrderID(orderID);
        }
        orderID++;
        return order.getOrderID();
    }

    void removeOrder(int orderID){
        if (orders.containsKey(orderID)) {
            orders.remove(orderID);
        }
    }

    void updateOrder(Order order){
        orders.put(order.getOrderID(), order);
    }

    void updateState(int orderID, OrderState orderState){
        Order orderComponent = orders.get(orderID);
        if (orderComponent != null){
            orderComponent.setOrderState(orderState);
            orders.put(orderID, orderComponent);
        }
    }

    Order searchOrder(int orderID){
        return orders.get(orderID);
    }

    Map<Integer, Order> getAllOrders(){
        return orders;
    }

}
