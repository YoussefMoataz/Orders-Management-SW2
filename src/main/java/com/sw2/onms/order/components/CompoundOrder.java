package com.sw2.onms.order.components;

import com.sw2.onms.product.model.Product;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
@DiscriminatorColumn(name = "compound_order")
public class CompoundOrder extends OrderComponent{

    private List<OrderComponent> components;

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

    public void addComponent(OrderComponent component){
        components.add(component);
    }

}
