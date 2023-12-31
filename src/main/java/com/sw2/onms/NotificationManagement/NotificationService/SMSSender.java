package com.sw2.onms.NotificationManagement.NotificationService;

public class SMSSender extends NotificationSender {


    public String send(String message, String contentAddress) {
        this.contentAddress = contentAddress;
        if (isValidPhoneNumber(contentAddress))
            return "The notification has been successfully sent via SMS.";
        return "Incorrect, Phone number must be 11 numbers and start with (011, 010, 012, or 015) ";
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Check if the phoneNumber starts with 011, 010, 012, or 015
        return phoneNumber != null && phoneNumber.length() == 11 && (phoneNumber.startsWith("011") || phoneNumber.startsWith("010") || phoneNumber.startsWith("012") || phoneNumber.startsWith("015"));
    }
}
