package com.sw2.onms.NotificationManagement.controller;

import com.sw2.onms.NotificationManagement.NotificationService.NotificationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/notifications")
public class NotificationController {
    NotificationManager notificationService;
    NotificationController(NotificationManager notificationService){
        this.notificationService = notificationService;
    }

    @GetMapping("/getMostSentTemplates")
    public String getMostSentTemplates(){
        return notificationService.getMostSentTemplates();
    }
    @GetMapping("/getMostNotifiedContactAddresses")
    public String MostNotifiedContactAddresses(){

        return notificationService.getMostNotifiedContactAddresses();
    }

}


