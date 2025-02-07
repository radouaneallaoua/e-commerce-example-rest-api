package com.allaoua.e_commerce.exceptions;

public class ProductNotFoundException extends  RuntimeException{
    public ProductNotFoundException(String message){
        super(message);
    }
}
