package com.sw2.onms.order;

import com.sw2.onms.order.components.Order;
import com.sw2.onms.order.components.OrderComponent;
import org.springframework.data.repository.Repository;

public interface OrderRepository extends Repository<OrderComponent, Integer> {

    void addOrder(Order order);
    void removeOrder(int orderID);
    void updateState(int orderID, OrderState orderState);
    OrderComponent searchOrder(int orderID);

}
