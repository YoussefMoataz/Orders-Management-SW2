package com.sw2.onms.order.components;

import com.sw2.onms.product.model.Product;

public class Order extends OrderComponent{

    @Override
    public OrderComponent listDetails() {
        return this;
    }

}
