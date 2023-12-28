package com.sw2.onms.product.controller;

import com.sw2.onms.product.model.Product;
import com.sw2.onms.product.service.ProductsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    ProductsService productsService;

    Product addProduct(Product product){return productsService.addProduct(product);}
    Product updateProduct(long serialNumber, Product product){return productsService.updateProduct(serialNumber, product);}
    Product deleteProduct(Long serialNumber){return productsService.deleteProduct(serialNumber);}
    Product getProductBySerialNumber(long serialNumber){return productsService.getProductBySerialNumber(serialNumber);}
    Product getProductByName(String name){return productsService.getProductByName(name);}
    List<Product> getProductsByCategory(String category){return productsService.getProductsByCategory(category);}
    List<Product> getProducts(){return productsService.getProducts();}
    List<Product> getAvailableProducts(){return productsService.getAvailableProducts();}
}
