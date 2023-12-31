package com.sw2.onms.NotificationManagement.controller;

import com.sw2.onms.NotificationManagement.NotificationService.NotificationManager;
import com.sw2.onms.customer.model.Customer;
import com.sw2.onms.customer.repo.CustomersRepo;
import com.sw2.onms.customer.service.CustomersService;

import java.util.List;
package com.sw2.onms.customer.controller;
@RestController
@RequestMapping("/notifications")
public class NotificationController {
    NotificationManager notificationService = new NotificationManager();
    @GetMapping("/getMostSentTemplates")
    public String getMostSentTemplates(){
        return notificationService.getMostSentTemplates();
    }
    @GetMapping("/getMostNotifiedContactAddresses")
    public String MostNotifiedContactAddresses(){
        return notificationService.getMostNotifiedContactAddresses();
    }

}


