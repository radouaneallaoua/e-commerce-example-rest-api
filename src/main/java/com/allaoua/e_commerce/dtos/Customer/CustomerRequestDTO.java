package com.allaoua.e_commerce.dtos.Customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.bind.annotation.ResponseBody;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class CustomerRequestDTO {
    @NotBlank
    private String name;
    @Email(message = "please enter a valid email")
    private String email;
    @NotNull
    private String phone;
}
