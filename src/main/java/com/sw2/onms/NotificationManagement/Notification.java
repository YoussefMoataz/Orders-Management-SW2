package com.sw2.onms.NotificationManagement;

public class Notification {
    String message;
    NotificationSenderType senderType;
    String contactAddress;
    public Notification(String message, NotificationSenderType senderType, String contactAddress){
        this.message = message;
        this.senderType = senderType;
        this.contactAddress = contactAddress;
    }
    public String getContactAddress() {
        return contactAddress;
    }
    public String getMessage() {
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
