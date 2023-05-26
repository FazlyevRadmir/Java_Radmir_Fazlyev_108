//HomeWork 3

package com.fazlyev.controller;

import com.fazlyev.model.Order;
import com.fazlyev.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") Long orderId) {
        Optional<Order> order = orderService.getOrderById(orderId);
        if (!order.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(order.get());
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }
}
