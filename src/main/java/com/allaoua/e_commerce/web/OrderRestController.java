package com.allaoua.e_commerce.web;

import com.allaoua.e_commerce.dtos.Order.OrderRequestDTO;
import com.allaoua.e_commerce.dtos.Order.OrderResponseDTO;
import com.allaoua.e_commerce.enums.OrderStatus;
import com.allaoua.e_commerce.services.IOrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderRestController {
    private final IOrderService orderService;

    public OrderRestController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<OrderResponseDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/by-status")
    public List<OrderResponseDTO> getOrdersByStatus(@RequestParam OrderStatus status) {
        return orderService.getOrdersByStatus(status);
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDTO saveOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        return orderService.saveOrder(orderRequestDTO);
    }

    @GetMapping("/orders/by-customer/{customerId}")
    public List<OrderResponseDTO> getOrdersByCustomer(@PathVariable String customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }

    @GetMapping("/orders/{orderId}")
    public OrderResponseDTO getOrderById(@PathVariable String orderId) {
        return orderService.getOrderById(orderId);
    }

    @PutMapping("/orders/{orderId}")
    public OrderResponseDTO updateOrder(@PathVariable String orderId, @Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        return orderService.updateOrder(orderId, orderRequestDTO);
    }

    @PutMapping("/orders/update-status/{orderId}")
    public OrderResponseDTO updateOrderStatus(@PathVariable String orderId, @RequestParam OrderStatus status) {
        return orderService.updateOrderStatus(orderId, status);
    }

    @DeleteMapping("/orders/{orderId}")
    public String deleteOrderById(@PathVariable String orderId) {
        return orderService.deleteOrder(orderId);
    }

}
