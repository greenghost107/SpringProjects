package com.example.Transportation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Michael on 6/5/2017.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No Such Driver")
public class DriverNotFoundException extends RuntimeException{
    public DriverNotFoundException(Long driverId)
    {
        super("could not find driver with " + driverId + " id.");
    }
}
