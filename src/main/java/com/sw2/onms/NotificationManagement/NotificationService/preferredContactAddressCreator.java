package com.sw2.onms.NotificationManagement.NotificationService;

import com.sw2.onms.customer.model.Customer;
public class preferredContactAddressCreator {
    public String createPreferredContactAddress(Customer customer){
        if(customer.getPreferredNotificationChannel() == NotificationSenderType.Email){
            return customer.getEmail();
        } else if (customer.getPreferredNotificationChannel() == NotificationSenderType.SMS) {
            return customer.getMobileNumber();
        }
        return null;
    }
}
