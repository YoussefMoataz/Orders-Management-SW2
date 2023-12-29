package com.sw2.onms.customer.model;

import com.sw2.onms.NotificationManagement.NotificationSenderType;
import com.sw2.onms.NotificationManagement.TemplateCreation.Language;

public class Customer {
    private String name;
    private String email;
    private String password;
    private Double balance;
    private String mobileNumber;
    private Language preferredLanguage;
    private NotificationSenderType preferredNotificationChannel;

    public Customer(String name, String email, String password, Double balance, String mobileNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.mobileNumber = mobileNumber;
        this.preferredLanguage = Language.English;
        this.preferredNotificationChannel = NotificationSenderType.Email;
    }

    public Customer(String name,
                    String email,
                    String password,
                    Double balance,
                    String mobileNumber,
                    Language preferredLanguage,
                    NotificationSenderType preferredNotificationChannel) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.mobileNumber = mobileNumber;
        this.preferredLanguage = preferredLanguage;
        this.preferredNotificationChannel = preferredNotificationChannel;
    }
    public Customer(String name,
                    String email,
                    String password,
                    Double balance,
                    String mobileNumber,
                    Language preferredLanguage) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.mobileNumber = mobileNumber;
        this.preferredLanguage = preferredLanguage;
        this.preferredNotificationChannel = NotificationSenderType.Email;
    }
    public Customer(String name,
                    String email,
                    String password,
                    Double balance,
                    String mobileNumber,
                    NotificationSenderType preferredNotificationChannel) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.mobileNumber = mobileNumber;
        this.preferredLanguage = Language.English;
        this.preferredNotificationChannel = preferredNotificationChannel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Language getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(Language preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public NotificationSenderType getPreferredNotificationChannel() {
        return preferredNotificationChannel;
    }

    public void setPreferredNotificationChannel(NotificationSenderType preferredNotificationChannel) {
        this.preferredNotificationChannel = preferredNotificationChannel;
    }
}
