package com.sw2.onms.NotificationManagement;

import com.sw2.onms.NotificationManagement.TemplateCreation.Template;

public class Notification {
    Template message;
    NotificationSenderType senderType;
    String contactAddress;
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
