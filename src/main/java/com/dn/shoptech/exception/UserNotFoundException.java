package com.dn.shoptech.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
