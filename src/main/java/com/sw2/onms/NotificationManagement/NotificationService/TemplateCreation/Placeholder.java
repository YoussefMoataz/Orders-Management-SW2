package com.sw2.onms.NotificationManagement.NotificationService.TemplateCreation;

public enum Placeholder {
    CustomerName, OrderID, OrderPrice, DetailedOrderContent, Products;

    public static Placeholder found(String val) {
        for (Placeholder p : Placeholder.values()) {
            if (p.name().equals(val)) {
                return p;
            }
        }
        return null;  // Return null if not found
    }
}
