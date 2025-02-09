package com.allaoua.e_commerce.repositories;

import com.allaoua.e_commerce.entities.OrderProduct;
import com.allaoua.e_commerce.entities.OrderProductKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductKey> {

}
