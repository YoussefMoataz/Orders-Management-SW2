package com.sw2.onms.NotificationManagement.TemplateCreation;

public enum Placeholder {
    Customer,OrderName,OrderPrice,DetailedOrderContent,Products;
    public static Placeholder found(String val) {
        for (Placeholder p : Placeholder.values()) {
            if (p.name().equals(val)) {
                return p;
            }
        }
        return null;  // Return null if not found
    }
}
/*

public boolean found(String val) {
        for (Placeholder p : Placeholder.values()) {
            if (p.name().equals(val)) {
                return true;
            }
        }
        return false;
    }
*/