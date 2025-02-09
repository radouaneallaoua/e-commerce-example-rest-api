package com.allaoua.e_commerce.exceptions;

public class OrderNotFoundException extends  RuntimeException{
    public OrderNotFoundException(String message){
        super(message);
    }
}
