package com.sw2.onms.order;

import com.sw2.onms.NotificationManagement.NotificationService.NotificationService;
import com.sw2.onms.NotificationManagement.NotificationService.Operation;
import com.sw2.onms.customer.model.Customer;
import com.sw2.onms.customer.repo.CustomersRepo;
import com.sw2.onms.product.model.Product;
import com.sw2.onms.product.repo.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Hassan Magdi
 * @author Youssef Moataz
 */
@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    private ProductsRepo productsRepo;
    private NotificationService notificationManager;
    private CustomersRepo customersRepo;
    private int shippingFees = 50;
    private Long validTime = 30000l;

    public OrderService(NotificationService notificationManager, CustomersRepo customersRepo) {
        this.notificationManager = notificationManager;
        this.customersRepo = customersRepo;
        this.orderRepository = OrderRepository.getInstance();
        this.productsRepo = new ProductsRepo();
        generateDummyOrders();
    }

    public Map<Integer, Order> listOrdersDetails() {
        return orderRepository.getAllOrders();
    }

    public void placeOrder(Order order) {
        order.setOrderState(OrderState.PLACED);
        Customer customer1 = order.getCustomer();
        if (order.getProductsCount() != null) {
            order.setProducts(order.getProductsCount());
        }
        customer1.setBalance(customer1.getBalance() - order.getPrice());
        customersRepo.update(customer1.getEmail(), customer1);
        for (long productSerial : order.getProductsSerialNumbers()) {
            Product product = productsRepo.getBySerialNumber(productSerial);
            product.setCount(product.getCount() - order.getCount(productSerial));
            productsRepo.update(productSerial, product);
        }
        for (Order component : order.getComponents()) {
            component.setOrderState(OrderState.PLACED);
            if (component.getProductsCount() != null) {
                component.setProducts(component.getProductsCount());
            }
            component.getCustomer().setBalance(component.getCustomer().getBalance() - component.getPrice());
            customersRepo.update(component.getCustomer().getEmail(), component.getCustomer());
            for (long productSerial : component.getProductsSerialNumbers()) {
                Product product = productsRepo.getBySerialNumber(productSerial);
                product.setCount(product.getCount() - component.getCount(productSerial));
                productsRepo.update(productSerial, product);
            }
        }
        orderRepository.addOrder(order);
        notificationManager.sendNotification(Operation.OrderPlacement, order);
    }

    public void shipOrder(int orderID) {
        orderRepository.updateState(orderID, OrderState.SHIPPING);
        Order order = orderRepository.searchOrder(orderID);
        order.setShippingTime(Time.valueOf(LocalTime.now()).getTime());
        order.getCustomer().setBalance(order.getCustomer().getBalance() - shippingFees);
        for (Order component : order.getComponents()) {
            component.setOrderState(OrderState.SHIPPING);
            component.getCustomer().setBalance(component.getCustomer().getBalance() - shippingFees);
        }
        orderRepository.updateOrder(order);
        notificationManager.sendNotification(Operation.OrderShipment, order);
    }

    public boolean cancelOrder(int orderID) {
        Order order = orderRepository.searchOrder(orderID);

        if (order.getOrderState() == OrderState.PLACED) {
            order.setOrderState(OrderState.CANCELLED);
            for (Long productSN : order.getProductsSerialNumbers()) {
                Product product = productsRepo.getBySerialNumber(productSN);
                product.setCount(product.getCount() + order.getCount(productSN));
            }
            order.getCustomer().setBalance(order.getCustomer().getBalance() + order.getPrice());
            customersRepo.update(order.getCustomer().getEmail(), order.getCustomer());
            for (Order component : order.getComponents()) {
                component.getCustomer().setBalance(component.getCustomer().getBalance() + component.getPrice());
                customersRepo.update(component.getCustomer().getEmail(), component.getCustomer());
                component.setOrderState(OrderState.CANCELLED);
                for (Long productSN : order.getProductsSerialNumbers()) {
                    Product product = productsRepo.getBySerialNumber(productSN);
                    product.setCount(product.getCount() + order.getCount(productSN));
                }
            }
            return true;
        }

        if (order.getOrderState() == OrderState.SHIPPING) {
            if (Time.valueOf(LocalTime.now()).getTime() - order.getShippingTime() <= validTime) {
                order.setOrderState(OrderState.CANCELLED);
                for (Long productSN : order.getProductsSerialNumbers()) {
                    Product product = productsRepo.getBySerialNumber(productSN);
                    product.setCount(product.getCount() + order.getCount(productSN));
                }
                order.getCustomer().setBalance(order.getCustomer().getBalance() + order.getPrice());
                customersRepo.update(order.getCustomer().getEmail(), order.getCustomer());
                for (Order component : order.getComponents()) {
                    component.getCustomer().setBalance(component.getCustomer().getBalance() + component.getPrice());
                    customersRepo.update(component.getCustomer().getEmail(), component.getCustomer());
                    component.setOrderState(OrderState.CANCELLED);
                    for (Long productSN : order.getProductsSerialNumbers()) {
                        Product product = productsRepo.getBySerialNumber(productSN);
                        product.setCount(product.getCount() + order.getCount(productSN));
                    }
                }
                return true;
            }
        }
        return false;
    }

    private void generateDummyOrders() {
        Customer customer1 = customersRepo.get("hassan@gmail.com");
        customer1.setBalance(12000.0);
        Customer customer2 = customersRepo.get("maged@gmail.com");
        customer2.setBalance(11000.0);
        Customer customer3 = customersRepo.get("youssef@gmail.com");
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

        Order o1 = new Order(productsList1, new ArrayList<>(), "dokki", customer1);
        Order o2 = new Order(productsList2, new ArrayList<>(), "gam3a", customer2);
        orderList1.add(o1);
        orderList1.add(o2);
        Order o3 = new Order(productsList3, orderList1, "haram", customer3);
        placeOrder(o3);
    }

}
