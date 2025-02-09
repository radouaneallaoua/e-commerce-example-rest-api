package com.allaoua.e_commerce.exceptions;

public class EmailAlreadyExistsException extends  RuntimeException{
    public EmailAlreadyExistsException(String message){
        super(message);
    }
}
