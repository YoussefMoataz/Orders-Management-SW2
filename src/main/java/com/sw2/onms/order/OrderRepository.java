package com.sw2.onms.order;

import java.util.HashMap;
import java.util.Map;

/**
 * This class follows Singleton pattern to provide consistency for the data. (Simulating database)
 *
 * @author Youssef Moataz
 * @author Hassan Magdi
 */
public class OrderRepository {

    private static OrderRepository orderRepository;
    private static Integer orderID = 1;
    Map<Integer, Order> orders;

    private OrderRepository() {
        orders = new HashMap<>();
    }

    public static OrderRepository getInstance() {
        if (orderRepository == null) {
            orderRepository = new OrderRepository();
        }
        return orderRepository;
    }

    /**
     * This function takes, stores the order and autogenerate its ID.
     *
     * @param order
     * @return The autogenerated ID.
     */
    int addOrder(Order order) {
        order.setOrderID(orderID);
        for (Order o : order.getComponents()) {
            o.setOrderID(orderID);
        }
        orders.put(orderID, order);
        orderID++;
        return order.getOrderID();
    }

    void removeOrder(int orderID) {
        if (orders.containsKey(orderID)) {
            orders.remove(orderID);
        }
    }

    void updateOrder(Order order) {
        orders.put(order.getOrderID(), order);
    }

    void updateState(int orderID, OrderState orderState) {
        Order orderComponent = orders.get(orderID);
        if (orderComponent != null) {
            orderComponent.setOrderState(orderState);
            orders.put(orderID, orderComponent);
        }
    }

    Order searchOrder(int orderID) {
        return orders.get(orderID);
    }

    Map<Integer, Order> getAllOrders() {
        return orders;
    }

}
