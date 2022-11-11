package com.example.Warehouse.Management.System.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public final class InvalidOldPasswordException extends RuntimeException {

    public InvalidOldPasswordException() {
        super();
    }

}
