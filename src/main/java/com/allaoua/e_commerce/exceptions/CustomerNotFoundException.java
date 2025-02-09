package com.allaoua.e_commerce.exceptions;

public class CustomerNotFoundException extends  RuntimeException{
    public CustomerNotFoundException(String message){
        super(message);
    }
}
