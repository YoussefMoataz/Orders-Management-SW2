package com.sw2.onms.product.model;

public class Product {
    private long serialNumber;
    private String name;
    private String vendor;
    private String category;
    private Double price;
    private int count;

    public Product() {
    }

    public Product(String name, String vendor, String category, Double price, int count) {
        this.name = name;
        this.vendor = vendor;
        this.category = category;
        this.price = price;
        this.count = count;
    }

    public Product(long serialNumber, String name, String vendor, String category, Double price, int count) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.vendor = vendor;
        this.category = category;
        this.price = price;
        this.count = count;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
