package com.sw2.onms.NotificationManagement;

public class SMSSender  extends NotificationSender{
    private String phoneNumber;
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String send(){
        if(isValidPhoneNumber())
            return "The notification has been successfully sent via SMS.";
        return "Incorrect, Phone number must be 11 numbers and start with (011, 010, 012, or 015) ";
    }
    private boolean isValidPhoneNumber() {
        // Check if the phoneNumber starts with 011, 010, 012, or 015
        return phoneNumber != null && phoneNumber.length() == 11 && (phoneNumber.startsWith("011") || phoneNumber.startsWith("010") || phoneNumber.startsWith("012")|| phoneNumber.startsWith("015"));
    }
}
