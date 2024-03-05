package com.auzzythebear.exceptions;

public class AuthenticationFailException extends IllegalArgumentException {
  public AuthenticationFailException(String message) {
    super(message);
  }
}
