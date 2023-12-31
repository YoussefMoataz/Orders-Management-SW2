package com.sw2.onms.NotificationManagement;

import java.util.*;

import com.sw2.onms.NotificationManagement.TemplateCreation.Placeholder;
import com.sw2.onms.NotificationManagement.TemplateCreation.Template;
import com.sw2.onms.NotificationManagement.TemplateCreation.TemplateCreator;
import com.sw2.onms.customer.model.Customer;
import com.sw2.onms.order.Order;

public class NotificationManager {
    private Queue<Notification> notificationQueue = new LinkedList<>() ;
    private  Map<String,Map<Placeholder, String>> CustomerPlaceholders = new HashMap<>();
    private  Map<String, Customer> orderCustomers = new HashMap<>();
    private  Map<String, Integer> mostNotifiedContactAddresses = new HashMap<>();
    private  Map<String, Integer> mostSentTemplates = new HashMap<>();
    private TemplateCreator templateCreator = new TemplateCreator();
    private Map<NotificationSenderType,Class<? extends NotificationSender>>senders;
    public NotificationManager(){
        senders = new HashMap<>();
        senders.put(NotificationSenderType.Email,EmailSender.class);
        senders.put(NotificationSenderType.SMS,SMSSender.class);
    }
    public  String sendNotification(Operation operation, Order order){
        if(order != null){
            reqGetPLaceholders(order);
            for (Map.Entry<String, Customer> entry : orderCustomers.entrySet()) {
                Template messageToBeSent = templateCreator.createTemplate(operation,CustomerPlaceholders.get(entry.getKey()),entry.getValue().getPreferredLanguage());
                Notification newNotification = new Notification(messageToBeSent,entry.getValue().getPreferredNotificationChannel(),entry.getKey());
                notificationQueue.add(newNotification);
                //System.out.println(newNotification.getMessage().getLatestSentTemplate());
                System.out.println(newNotification.getMessage().getContentWithActualVal());
            }
            return "Notification has been sent successfully";
        }
        return "Notification has not been sent, as order is null";
    }
    public String getMostSentTemplates(){
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(mostSentTemplates.entrySet());
        entryList.sort(Map.Entry.<String, Integer>comparingByValue().reversed());
        String sortedTemplates = "";
        // Iterate over the sorted Templates according to its frequency
        for (Map.Entry<String, Integer> entry : entryList) {
            sortedTemplates += "Template: \n" + entry.getKey() + "\n, Frequency: " + entry.getValue()+"\n";
        }
        return sortedTemplates;
    }
    private void SendNotificationFromQueue(){
        Notification notificationToBeSent = notificationQueue.poll();
        if(senders.containsKey(notificationToBeSent.getSenderType())) {
            try{
                NotificationSender sender = senders.get(notificationToBeSent.getSenderType()).newInstance();
                //If method send returns a message contains Incorrect word this is means that contact address is incorrect
                if(!sender.send(notificationToBeSent.getMessage().getContentWithActualVal(),notificationToBeSent.getContactAddress()).contains("Incorrect")){
                    int counter = 1;
                    if(mostSentTemplates.containsKey(notificationToBeSent.getMessage().getLatestSentTemplate())){
                        counter = mostSentTemplates.get(notificationToBeSent.getMessage().getLatestSentTemplate()) + 1;
                        mostSentTemplates.put(notificationToBeSent.getMessage().getLatestSentTemplate(), counter);
                    }else{
                        mostSentTemplates.put(notificationToBeSent.getMessage().getLatestSentTemplate(), 1);
                    }
                    if(mostNotifiedContactAddresses.containsKey(notificationToBeSent.getContactAddress())){
                        counter = mostNotifiedContactAddresses.get(notificationToBeSent.getContactAddress()) + 1;
                        mostNotifiedContactAddresses.put(notificationToBeSent.getContactAddress(), counter);
                    }else
                        mostNotifiedContactAddresses.put(notificationToBeSent.getContactAddress(), 1);

                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace(); // Handle the exception appropriately in your application
            }
        }
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
