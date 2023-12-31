package com.sw2.onms.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Hassan Magdi
 * @author Youssef Moataz
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/list_details")
    public ResponseEntity<Map<Integer, Order>> listDetails(){
        return new ResponseEntity<>(orderService.listOrdersDetails(), HttpStatus.OK);
    }

    @PostMapping (value = "/place_order")
    public ResponseEntity<String> placeOrder(@RequestBody Order order){
        orderService.placeOrder(order);
        return new ResponseEntity<>("* Check the console for the notification", HttpStatus.CREATED);
    }

    @PutMapping (value = "/ship/{ID}")
    public ResponseEntity<String> shipOrder(@PathVariable("ID") int ID){
        orderService.shipOrder(ID);
        return new ResponseEntity<>("* Check the console for the notification", HttpStatus.ACCEPTED);
    }

    @PutMapping (value = "/cancel/{ID}")
    public ResponseEntity<String> cancelOrder(@PathVariable("ID") int ID){
        boolean isCancelled = orderService.cancelOrder(ID);
        if(isCancelled){
            return new ResponseEntity<>("Order cancelled successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Couldn't cancel order", HttpStatus.OK);
        }
    }

}
