package com.sw2.onms.NotificationManagement;

public class EmailSender extends NotificationSender{
    private String emailAddress;
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    String send(){
        if(isValidEmailAddress())
            return "The notification has been successfully sent via email.";
        return "Incorrect, Email address must end with @gmail.com";
    }
    private boolean isValidEmailAddress() {
        // Check if the emailAddress ends with "@gmail.com"
        return emailAddress != null && emailAddress.endsWith("@gmail.com");
    }
}
