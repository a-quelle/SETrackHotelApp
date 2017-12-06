package com.SEtrack.Hotel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * NotFoundException
 * @author kgriffio
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Guest not found.")
public class NotFoundException extends RuntimeException{

}