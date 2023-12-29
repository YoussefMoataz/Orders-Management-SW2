package com.sw2.onms.order;

import com.sw2.onms.customer.model.Customer;
import com.sw2.onms.product.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private int shippingFees = 50;

    public OrderService() {
        this.orderRepository = new OrderRepository();
        generateDummyOrders();
    }

    public Map<Integer, Order> listOrdersDetails(){
        return orderRepository.getAllOrders();
    }

    public void placeOrder(Order order){
        order.setOrderState(OrderState.PLACED);
        Customer customer1 = order.getCustomer();
        customer1.setBalance(customer1.getBalance() - shippingFees - order.getPrice());
        for(Order component: order.getComponents()){
            component.getCustomer().setBalance(component.getCustomer().getBalance() - shippingFees - component.getPrice());
            component.setOrderState(OrderState.PLACED);
        }

        orderRepository.addOrder(order);
    }

    public void shipOrder(int orderID){
        orderRepository.updateState(orderID,OrderState.SHIPPING);
        for(Order component: orderRepository.searchOrder(orderID).getComponents()){
            component.setOrderState(OrderState.SHIPPING);
        }
    }

    private void generateDummyOrders(){
        Customer customer1=new Customer("hassan","hassan@gmail.com","12345678",10000000000.25);
        Customer customer2=new Customer("maged","maged@gmail.com","12345678",100000000000000.75);
        Customer customer3=new Customer("youssef","youssef@gmail.com","12345678",100000000000000.5);

        Product product1 = new Product("shampoo","metro","chemicals",65.0,1);
        Product product2 = new Product("fries", "raw", "food", 12.0, 1);

        List<Product> productsList1 = new ArrayList<>();
        productsList1.add(product1);
        productsList1.add(product2);

        List<Order> orderList1 = new ArrayList<>();

        Order o1 = new Order(productsList1,new ArrayList<>(),"dokki",customer1);
        Order o2 = new Order(productsList1, new ArrayList<>(), "gam3a", customer2);
        orderList1.add(o1);
        orderList1.add(o2);
        Order o3 = new Order(productsList1, orderList1, "haram", customer3);
        placeOrder(o3);
    }

}
