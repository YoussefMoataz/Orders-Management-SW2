package com.sw2.onms.product.service;

import com.sw2.onms.product.model.Product;
import com.sw2.onms.product.repo.ProductsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    private final ProductsRepo productsRepo;

    public ProductsService(ProductsRepo productsRepo) {
        this.productsRepo = productsRepo;
    }

    public Product addProduct(Product product) {
        return productsRepo.add(product);
    }

    public Product updateProduct(long serialNumber, Product product) {
        return productsRepo.update(serialNumber, product);
    }

    public Product deleteProduct(long serialNumber) {
        return productsRepo.delete(serialNumber);
    }

    public Product getProductBySerialNumber(long serialNumber) {
        return productsRepo.getBySerialNumber(serialNumber);
    }

    public Product getProductByName(String name) {
        return productsRepo.getByName(name);
    }

    public List<Product> getProductsByCategory(String category) {
        return productsRepo.getByCategory(category);
    }

    public List<Product> getProducts() {
        return productsRepo.getAll();
    }

    public List<Product> getAvailableProducts() {
        return productsRepo.getAvailable();
    }
}
