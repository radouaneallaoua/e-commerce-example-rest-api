package com.allaoua.e_commerce.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter @Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {
    @Id @GeneratedValue(strategy =  GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private double price;
    private int quantity;

    @ManyToOne
    private Category category;

}
