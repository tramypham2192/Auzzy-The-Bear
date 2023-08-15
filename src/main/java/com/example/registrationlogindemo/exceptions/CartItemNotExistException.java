package com.example.registrationlogindemo.exceptions;

public class CartItemNotExistException extends IllegalArgumentException {
    public CartItemNotExistException(String message){
        super(message);
    }
}
