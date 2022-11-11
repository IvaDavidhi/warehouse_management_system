package com.example.Warehouse.Management.System.exceptions;

public final class InvalidOldPasswordException extends RuntimeException {
    private String message;

    public InvalidOldPasswordException(String message){
        super(message);
    }

}
