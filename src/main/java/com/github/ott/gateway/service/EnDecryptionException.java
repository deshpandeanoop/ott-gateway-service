package com.github.ott.gateway.service;

public class EnDecryptionException extends RuntimeException {

    public EnDecryptionException(String message) {
        super(message);
    }

    public EnDecryptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
