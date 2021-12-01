package com.pawan.ecommerce.ecommerce.exception;

public class BadException extends Exception {
    public BadException(String incorrect_credentials) {
        super(incorrect_credentials);
    }
}
