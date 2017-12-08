package com.SEtrack.Hotel.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ForbiddenException
 * @author aquelle
 */
@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Invalid input.")
public class ForbiddenException extends RuntimeException{
}
