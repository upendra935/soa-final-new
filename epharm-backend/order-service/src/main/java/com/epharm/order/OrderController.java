package com.epharm.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Get all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {

        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new order
    @PostMapping
    public Order createOrder(@RequestBody Order order) {

        if (order.getOrderDate() == null) {
            order.setOrderDate(LocalDateTime.now());
        }

        if (order.getStatus() == null || order.getStatus().isBlank()) {
            order.setStatus("PENDING");
        }

        if (order.getOrderNumber() == null || order.getOrderNumber().isBlank()) {
            order.setOrderNumber("ORD-" + System.currentTimeMillis());
        }

        return orderRepository.save(order);
    }

    // Update an order
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(
            @PathVariable Long id,
            @RequestBody Order updatedOrder) {

        return orderRepository.findById(id)
                .map(existing -> {

                    existing.setOrderNumber(updatedOrder.getOrderNumber());
                    existing.setProductId(updatedOrder.getProductId());
                    existing.setProductName(updatedOrder.getProductName());
                    existing.setQuantity(updatedOrder.getQuantity());
                    existing.setSupplier(updatedOrder.getSupplier());
                    existing.setStatus(updatedOrder.getStatus());
                    existing.setTrackingNumber(updatedOrder.getTrackingNumber());

                    if (updatedOrder.getOrderDate() != null) {
                        existing.setOrderDate(updatedOrder.getOrderDate());
                    }

                    return ResponseEntity.ok(
                            orderRepository.save(existing)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete an order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {

        if (!orderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        orderRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}