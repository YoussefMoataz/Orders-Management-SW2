package com.sw2.onms.order;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping (value = "/place_order")
    public void placeOrder(@RequestBody Order order){
        orderService.placeOrder(order);
    }

    @PutMapping (value = "/ship/{ID}")
    public void shipOrder(@PathVariable("ID") int ID){
        orderService.shipOrder(ID);
    }

    @GetMapping("/list_details")
    public Map<Integer, Order> listDetails(){
        return orderService.listOrdersDetails();
    }
}
