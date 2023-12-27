package com.sw2.onms.order;

import com.sw2.onms.order.components.OrderComponent;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private int shippingFees;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void listOrdersDetails(){
        // todo
    }

    public void placeOrder(OrderComponent order){
        // todo
    }

    public void shipOrder(int orderID){
        // todo
    }

}
