package com.sw2.onms.order;

import com.sw2.onms.NotificationManagement.NotificationSenderType;
import com.sw2.onms.NotificationManagement.Operation;
import com.sw2.onms.NotificationManagement.TemplateCreation.Language;
import com.sw2.onms.customer.model.Customer;
import com.sw2.onms.customer.repo.CustomersRepo;
import com.sw2.onms.product.model.Product;
import com.sw2.onms.NotificationManagement.NotificationManager;
import com.sw2.onms.product.repo.ProductsRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private NotificationManager notificationManager = new NotificationManager();
    private int shippingFees = 50;

    public OrderService() {
        this.orderRepository = OrderRepository.getInstance();
        generateDummyOrders();
    }

    public Map<Integer, Order> listOrdersDetails(){
        return orderRepository.getAllOrders();
    }

    public void placeOrder(Order order){
        order.setOrderState(OrderState.PLACED);
        Customer customer1 = order.getCustomer();
        customer1.setBalance(customer1.getBalance() - order.getPrice());
        for(Order component: order.getComponents()){
            component.setOrderState(OrderState.PLACED);
            component.getCustomer().setBalance(component.getCustomer().getBalance()- component.getPrice());
        }
        notificationManager.sendNotification(Operation.OrderPlacement,order);
        orderRepository.addOrder(order);
    }

    public void shipOrder(int orderID){
        orderRepository.updateState(orderID,OrderState.SHIPPING);
        Order order = orderRepository.searchOrder(orderID);
        order.getCustomer().setBalance(order.getCustomer().getBalance()-shippingFees);
        for(Order component: order.getComponents()){
            component.setOrderState(OrderState.SHIPPING);
            component.getCustomer().setBalance(component.getCustomer().getBalance()-shippingFees);
        }
        orderRepository.updateOrder(order);
    }

    private void generateDummyOrders(){
        CustomersRepo customersRepo = new CustomersRepo();
        Customer customer1=customersRepo.get("hassan@gmail.com");
        customer1.setBalance(12000.0);
        Customer customer2=customersRepo.get("maged@gmail.com");
        customer2.setBalance(11000.0);
        Customer customer3=customersRepo.get("youssef@gmail.com");
        customer3.setBalance(10500.0);

        ProductsRepo productsRepo = new ProductsRepo();
        Product product1 = productsRepo.getBySerialNumber(10001L);
        product1.setCount(1);
        Product product2 = productsRepo.getBySerialNumber(10002L);
        product2.setCount(1);
        Product product3 = productsRepo.getBySerialNumber(10003L);
        product3.setCount(1);
        Product product4 = productsRepo.getBySerialNumber(10004L);
        product4.setCount(4);
        Product product5 = productsRepo.getBySerialNumber(10006L);
        product5.setCount(4);

        List<Product> productsList1 = new ArrayList<>();
        productsList1.add(product1);
        productsList1.add(product4);

        List<Product> productsList2 = new ArrayList<>();
        productsList2.add(product2);

        List<Product> productsList3 = new ArrayList<>();
        productsList3.add(product1);
        productsList3.add(product5);

        List<Order> orderList1 = new ArrayList<>();

        Order o1 = new Order(productsList1,new ArrayList<>(),"dokki",customer1);
        Order o2 = new Order(productsList2, new ArrayList<>(), "gam3a", customer2);
        orderList1.add(o1);
        orderList1.add(o2);
        Order o3 = new Order(productsList3, orderList1, "haram", customer3);
        placeOrder(o3);
    }

}
