package com.sw2.onms.product.repo;

import com.sw2.onms.product.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class ProductsRepo {
    private final HashMap<Long, Product> products;

    public ProductsRepo() {
        products = new HashMap<>();

        // dummy products for testing purpose
        products.put(10001L, new Product(10001L, "samsung phone", "samsung", "mobile phones", 7000.0, 10));
        products.put(10002L, new Product(10002L, "oppo phone", "oppo", "mobile phones", 9100.0, 2));
        products.put(10003L, new Product(10003L, "iphone", "apple", "mobile phones", 100000.0, 7));
        products.put(10004L, new Product(10004L, "molto", "edita", "snacks", 10.0, 100));
        products.put(10005L, new Product(10005L, "big chips", "egypt foods", "snacks", 5.0, 150));
        products.put(10006L, new Product(10006L, "spiro spathis", "spiro spathis", "beverages", 12.0, 0));
        products.put(10007L, new Product(10007L, "V7", "V7", "beverages", 15.0, 12));
    }

    public Product add(Product product) {
        products.put(product.getSerialNumber(), product);
        return products.get(product.getSerialNumber());
    }

    public Product update(long serialNumber, Product product) {
        products.put(serialNumber, product);
        return products.get(serialNumber);
    }

    public Product delete(long serialNumber) {
        return products.remove(serialNumber);
    }

    public Product getBySerialNumber(long serialNumber) {
        return products.get(serialNumber);
    }

    public Product getByName(String name) {
        for (Product product : products.values()) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    public List<Product> getByCategory(String category) {
        ArrayList<Product> matchingProducts = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getCategory().equals(category)) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    public List<Product> getAll() {
        return products.values().stream().toList();
    }

    public List<Product> getAvailable() {
        ArrayList<Product> availableProducts = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getCount() > 0) {
                availableProducts.add(product);
            }
        }
        return availableProducts;
    }
}
