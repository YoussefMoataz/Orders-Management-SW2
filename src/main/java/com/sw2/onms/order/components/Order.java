package com.sw2.onms.order.components;

import com.sw2.onms.product.model.Product;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorColumn(name = "order")
public class Order extends OrderComponent{
    @Override
    public void listDetails() {
        // todo
    }

    @Override
    public void addProduct(Product product) {
        // todo
    }

    @Override
    public void removeProduct(Product product) {
        // todo
    }

    @Override
    public double getPrice() {
        // todo
        return 0;
    }
}
