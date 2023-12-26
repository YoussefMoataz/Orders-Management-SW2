package com.sw2.onms.product.service;

import com.sw2.onms.product.model.Product;
import com.sw2.onms.product.repo.ProductsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    private final ProductsRepo productsRepo;
    ProductsService(ProductsRepo productsRepo){
        this.productsRepo = productsRepo;
    }
    public Product addProduct(Product product){return null;}
    public boolean updateProduct(long serialNumber, Product product){return false;}
    public boolean deleteProduct(Product product){return false;}
    public Product getProductBySerialNumber(long serialNumber){return null;}
    public Product getProductByName(String name){return null;}
    public List<Product> getProductsByCategory(String category){return null;}
    public List<Product> getProducts(){return null;}
    public List<Product> getAvailableProducts(){return null;}
}
