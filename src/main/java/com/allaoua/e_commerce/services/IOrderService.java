package com.allaoua.e_commerce.services;


import com.allaoua.e_commerce.dtos.Order.OrderRequestDTO;
import com.allaoua.e_commerce.dtos.Order.OrderResponseDTO;
import com.allaoua.e_commerce.enums.OrderStatus;
import com.allaoua.e_commerce.exceptions.CustomerNotFoundException;
import com.allaoua.e_commerce.exceptions.OrderNotFoundException;

import java.util.List;

public interface IOrderService {
    OrderResponseDTO saveOrder(OrderRequestDTO orderRequestDTO);
    OrderResponseDTO updateOrder(String orderId,OrderRequestDTO orderRequestDTO) throws OrderNotFoundException;
    String deleteOrder(String orderId) throws OrderNotFoundException;
    OrderResponseDTO getOrderById(String orderId) throws OrderNotFoundException;
    List<OrderResponseDTO> getAllOrders();
    List<OrderResponseDTO> getOrdersByCustomerId(String customerId) throws CustomerNotFoundException;
    List<OrderResponseDTO> getOrdersByStatus(OrderStatus status);
    OrderResponseDTO updateOrderStatus(String orderId,OrderStatus status) throws OrderNotFoundException;


}
