package com.pawan.ecommerce.exception;

public class BadException extends Exception {
    public BadException(String incorrect_credentials) {
        super(incorrect_credentials);
    }
}
