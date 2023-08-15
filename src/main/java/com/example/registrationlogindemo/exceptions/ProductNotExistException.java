package com.example.registrationlogindemo.exceptions;

public class ProductNotExistException extends IllegalArgumentException {
    public ProductNotExistException(String message){
        super(message);
    }
}
