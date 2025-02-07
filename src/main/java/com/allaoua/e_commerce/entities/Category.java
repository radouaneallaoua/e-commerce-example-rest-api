package com.allaoua.e_commerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter @Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

}
