package com.example.registrationlogindemo.exceptions;

public class AuthenticationFailException extends IllegalArgumentException {
    public AuthenticationFailException(String message){
        super(message);
    }
}
