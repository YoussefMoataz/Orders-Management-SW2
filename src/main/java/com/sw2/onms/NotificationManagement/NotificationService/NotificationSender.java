package com.sw2.onms.NotificationManagement.NotificationService;

public abstract class NotificationSender {
    protected String message;
    protected String contentAddress;

    public void setMessage(String message) {
        this.message = message;
    }

    abstract String send(String message, String contentAddress);
}
