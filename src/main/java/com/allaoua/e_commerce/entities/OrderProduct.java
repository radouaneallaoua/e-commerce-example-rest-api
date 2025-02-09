package com.allaoua.e_commerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter @Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderProduct {
    @EmbeddedId
    private OrderProductKey id;
    private int quantity;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;


}
