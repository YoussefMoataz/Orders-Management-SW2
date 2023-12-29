package com.sw2.onms.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> placeOrder(@RequestBody Order order){
        orderService.placeOrder(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping (value = "/ship/{ID}")
    public ResponseEntity<Void> shipOrder(@PathVariable("ID") int ID){
        orderService.shipOrder(ID);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/list_details")
    public ResponseEntity<Map<Integer, Order>> listDetails(){
        return new ResponseEntity<>(orderService.listOrdersDetails(), HttpStatus.OK);
    }
}
