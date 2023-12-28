package com.sw2.onms.product.controller;

import com.sw2.onms.product.model.Product;
import com.sw2.onms.product.repo.ProductsRepo;
import com.sw2.onms.product.service.ProductsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    ProductsService productsService;
    ProductController(ProductsRepo productsRepo){
        this.productsService = new ProductsService(productsRepo);
    }

    @PostMapping("/add")
    Product addProduct(@RequestBody Product product){return productsService.addProduct(product);}
    @PutMapping("/update/{serialNumber}")
    Product updateProduct(@PathVariable long serialNumber, @RequestBody Product product){return productsService.updateProduct(serialNumber, product);}
    @DeleteMapping("/delete/{serialNumber}")
    Product deleteProduct(@PathVariable long serialNumber){return productsService.deleteProduct(serialNumber);}
    @GetMapping("/search/serial/{serialNumber}")
    Product getProductBySerialNumber(@PathVariable long serialNumber){return productsService.getProductBySerialNumber(serialNumber);}
    @GetMapping("/search/name/{name}")
    Product getProductByName(@PathVariable String name){return productsService.getProductByName(name);}
    @GetMapping("/search/category/{category}")
    List<Product> getProductsByCategory(@PathVariable String category){return productsService.getProductsByCategory(category);}
    @GetMapping("/search/all")
    List<Product> getProducts(){return productsService.getProducts();}
    @GetMapping("search/available")
    List<Product> getAvailableProducts(){return productsService.getAvailableProducts();}
}
