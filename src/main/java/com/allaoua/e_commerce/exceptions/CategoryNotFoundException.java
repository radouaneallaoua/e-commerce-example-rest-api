package com.allaoua.e_commerce.exceptions;

public class CategoryNotFoundException extends  RuntimeException{
    public CategoryNotFoundException(String message){
        super(message);
    }
}
