package com.sw2.onms.product.repo;

import com.sw2.onms.product.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductsRepo {
    private final HashMap<Long, Product> products;

    ProductsRepo(){
        products = new HashMap<>();
    }

    public Product add(Product product){
        products.put(product.getSerialNumber(), product);
        return products.get(product.getSerialNumber());
    }

    public Product update(long serialNumber, Product product){
        products.put(serialNumber, product);
        return products.get(serialNumber);
    }

    public Product delete(long serialNumber){
        return products.remove(serialNumber);
    }

    public Product getBySerialNumber(long serialNumber){
        return products.get(serialNumber);
    }

    public Product getByName(String name){
        for(Product product: products.values()){
            if(product.getName().equals(name)){
                return product;
            }
        }
        return null;
    }

    public List<Product> getByCategory(String category){
        ArrayList<Product> matchingProducts = new ArrayList<>();
        for(Product product: products.values()){
            if(product.getCategory().equals(category)){
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    public List<Product> getAll(){
        return products.values().stream().toList();
    }

    public List<Product> getAvailable(){
        ArrayList<Product> availableProducts = new ArrayList<>();
        for(Product product: products.values()){
            if(product.getCount() > 0){
                availableProducts.add(product);
            }
        }
        return availableProducts;
    }
}
