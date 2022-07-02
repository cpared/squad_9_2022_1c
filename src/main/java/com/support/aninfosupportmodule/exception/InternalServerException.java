package com.support.aninfosupportmodule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends Exception {

    public InternalServerException(String message) {
        super(message);
    }
}
