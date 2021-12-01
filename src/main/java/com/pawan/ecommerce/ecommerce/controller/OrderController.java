package com.pawan.ecommerce.ecommerce.controller;


import com.pawan.ecommerce.ecommerce.dto.OrderRequest;
import com.pawan.ecommerce.ecommerce.model.Order;
import com.pawan.ecommerce.ecommerce.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@Slf4j
@CrossOrigin(value = "*")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> createorder(@RequestBody OrderRequest orderRequest)
    {

        JSONObject jsonObject = new JSONObject();
        orderService.create_order(orderRequest);
        jsonObject.put("Status",200);
        jsonObject.put("message","Sucessfully added order");
        return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
    }

    @GetMapping("/getAllOrder")
    public List<Order> getAllOrders(){

        return orderService.getAllProducts();
    }

    @GetMapping("/getOrder/{id}")
    public Order getOrder(@PathVariable int id){

        return orderService.getOrderByID(id);
    }

    @PutMapping("/updateOrder/{id}")
    public void updateOrder(@PathVariable int id, @RequestBody OrderRequest orderRequest){

        Order order = orderService.getOrderByID(id);
        orderService.updateOrder(order,orderRequest);
    }
    @GetMapping("/user/{userName}")
    public ResponseEntity<?> getOrderByUser(@PathVariable String userName){
        JSONObject jsonObject = new JSONObject();
        List<Order> orders = orderService.getOrderByUser(userName);
        if(orders.size()>0){
            jsonObject.put("status",200);
            jsonObject.put("orderList",orders);
            return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
        }
        jsonObject.put("status",400);
        jsonObject.put("orderList","somethign went wrong");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonObject.toString());

    }


    @DeleteMapping("/deleteOrder/{id}")
    public void deleteOrder(@PathVariable int id){
        orderService.deleteOrder(id);
    }
}
