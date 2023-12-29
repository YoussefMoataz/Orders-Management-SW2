package com.sw2.onms.order;

import com.sw2.onms.order.components.Order;
import com.sw2.onms.order.components.OrderComponent;

import java.util.HashMap;
import java.util.Map;

public class OrderRepository {

    private static Integer orderID = 1;
    Map<Integer, OrderComponent> orders;

    public OrderRepository(){
        orders = new HashMap<>();
    }

    int addOrder(Order order){
        order.setOrderID(orderID);
        orders.put(orderID,order);
        orderID++;
        return order.getOrderID();
    }
    void removeOrder(int orderID){
        if (orders.containsKey(orderID)) {
            orders.remove(orderID);
        }
    }
    void updateState(int orderID, OrderState orderState){
        OrderComponent orderComponent = orders.get(orderID);
        if (orderComponent != null){
            orderComponent.setOrderState(orderState);
            orders.put(orderID, orderComponent);
        }
    }
    OrderComponent searchOrder(int orderID){
        return orders.get(orderID);
    }

}
