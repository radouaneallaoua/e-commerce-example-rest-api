package com.allaoua.e_commerce.exceptions;

import lombok.*;

import java.time.LocalDateTime;
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class ErrorMessage {
    private String message;
    private int code;
    private LocalDateTime timestamp;
}
