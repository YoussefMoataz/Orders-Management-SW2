package com.sw2.onms.order.components;

import com.sw2.onms.product.model.Product;

import java.util.List;

public class CompoundOrder extends OrderComponent{

    private List<OrderComponent> components;

    @Override
    public OrderComponent listDetails() {
        return this;
    }

    public void addComponent(OrderComponent component){
        components.add(component);
    }

}
