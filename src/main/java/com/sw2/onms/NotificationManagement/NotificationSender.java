package com.sw2.onms.NotificationManagement;

public abstract class NotificationSender {
    protected String message;
    protected String str;
    public void setMessage(String message){
        this.message = message;
    }
    abstract String send();
}
