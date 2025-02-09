package com.allaoua.e_commerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Embeddable
@Setter @Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderProductKey {
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "product_id")
    private String productId;


}
