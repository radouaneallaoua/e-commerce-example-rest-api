package com.allaoua.e_commerce.dtos.Customer;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class CustomerResponseDTO {
    private String id;
    private String name;
    private String email;
    private String phone;
}
