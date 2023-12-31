package com.sw2.onms.NotificationManagement;

public class EmailSender extends NotificationSender{

    String send(String message, String contentAddress){
        this.contentAddress = contentAddress;
        if(isValidEmailAddress(contentAddress))
            return "The notification has been successfully sent via email.";
        return "Incorrect, Email address must end with @gmail.com";
    }
    private boolean isValidEmailAddress(String emailAddress) {
        // Check if the emailAddress ends with "@gmail.com"
        return emailAddress != null && emailAddress.endsWith("@gmail.com");
    }
}
