package com.sw2.onms.NotificationManagement.NotificationService;

import com.sw2.onms.NotificationManagement.NotificationService.TemplateCreation.Template;

public class Notification {
    private Template message;
    private NotificationSenderType senderType;
    private String contactAddress;
    public Notification(Template message, NotificationSenderType senderType, String contactAddress){
        this.message = message;
        this.senderType = senderType;
        this.contactAddress = contactAddress;
    }
    public String getContactAddress() {
        return contactAddress;
    }
    public Template getMessage() {
        return message;
    }
    public NotificationSenderType getSenderType() {
        return senderType;
    }
    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public void setSenderType(NotificationSenderType senderType) {
        this.senderType = senderType;
    }


}
