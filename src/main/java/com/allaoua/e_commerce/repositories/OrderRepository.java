package com.allaoua.e_commerce.repositories;
import com.allaoua.e_commerce.entities.Order;
import com.allaoua.e_commerce.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String> {
    List<Order> findByCustomerId(String customerId);
    List<Order> findByStatus(OrderStatus status);
}
