package com.example.QuickPasta.controller;

import com.example.QuickPasta.dto.response.OrderResponse;
import com.example.QuickPasta.serviceInterface.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/placeOrder")
    public ResponseEntity placeOrder(@RequestParam("customerId") int customerId) {
        OrderResponse orderResponse = orderService.placeOrder(customerId);
        return new ResponseEntity(orderResponse, HttpStatus.CREATED);
    }

    @GetMapping("/viewOrders")
    public ResponseEntity viewAllOrders(@RequestParam("customerId") int customerId) {
        List<OrderResponse> orderResponses = orderService.viewAllOrders(customerId);
        return new ResponseEntity(orderResponses,HttpStatus.OK);
    }
}
