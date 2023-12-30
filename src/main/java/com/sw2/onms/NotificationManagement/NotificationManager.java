package com.sw2.onms.NotificationManagement;

import java.util.*;

import com.sw2.onms.NotificationManagement.TemplateCreation.Placeholder;
import com.sw2.onms.NotificationManagement.TemplateCreation.TemplateCreator;
import com.sw2.onms.customer.model.Customer;
import com.sw2.onms.order.Order;
import com.sw2.onms.order.OrderRepository;
import com.sw2.onms.product.model.Product;

public class NotificationManager {
    private OrderRepository orderRepository = OrderRepository.getInstance();
    private Queue<Notification> notificationQueue = new LinkedList<>() ;
    private  Map<String,Map<Placeholder, String>> CustomerPlaceholders = new HashMap<>();
    private  Map<String, Customer> orderCustomers = new HashMap<>();
    private TemplateCreator templateCreator = new TemplateCreator();

    public  String sendNotification(Operation operation, Order order){
        if(order != null){
            reqGetPLaceholders(order);
            for (Map.Entry<String, Customer> entry : orderCustomers.entrySet()) {
                String messageToBeSent = templateCreator.createTemplate(operation,CustomerPlaceholders.get(entry.getKey()),entry.getValue().getPreferredLanguage());
                Notification newNotification = new Notification(messageToBeSent,entry.getValue().getPreferredNotificationChannel(),entry.getKey());
                notificationQueue.add(newNotification);
                System.out.println(messageToBeSent);
            }
            return "Notification has been sent successfully";
        }
        return "Notification has not been sent, as order is null";
    }
    private void SendNotificationFromQueue(){

    }
    private void reqGetPLaceholders(Order curOrder){
        if( curOrder != null){
            //order.getCustomer() != null &&
            if(CustomerPlaceholders.containsKey(curOrder.getCustomer().getEmail())){
                Map<Placeholder, String> placeholders = CustomerPlaceholders.get(curOrder.getCustomer().getEmail());
                Double orderPrice = Double.parseDouble(placeholders.get(Placeholder.OrderPrice));
                orderPrice += curOrder.getPrice();
                placeholders.put(Placeholder.OrderPrice,String.valueOf(orderPrice));
                List<String> products = curOrder.getProductsNames();
                String productsName = placeholders.get(Placeholder.Products);
                for(String product:products){
                    productsName += ", " + product;
                }
                placeholders.put(Placeholder.Products, productsName);

            }else{
                orderCustomers.put(curOrder.getCustomer().getEmail(),curOrder.getCustomer());
                Map<Placeholder, String> placeholders = new HashMap<>();
                placeholders.put(Placeholder.CustomerName,curOrder.getCustomer().getName());
                placeholders.put(Placeholder.OrderID, String.valueOf(curOrder.getOrderID()));
                placeholders.put(Placeholder.OrderPrice, String.valueOf(curOrder.getPrice()));

                List<String> products = curOrder.getProductsNames();
                String productsName = "";
                for(String product:products){
                    productsName +=  ", " + product;
                }
                if(productsName.length() > 0) {
                    productsName = productsName.substring(2, productsName.length());
                }
                placeholders.put(Placeholder.Products, productsName);
                CustomerPlaceholders.put(curOrder.getCustomer().getEmail(),placeholders);
            }
            for(Order order : curOrder.getComponents() ){
                reqGetPLaceholders(order);
            }
        }
    }

}
