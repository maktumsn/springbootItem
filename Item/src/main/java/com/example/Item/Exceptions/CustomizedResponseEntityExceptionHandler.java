package com.example.Item.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse=new ExceptionResponse(404, ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse=new ExceptionResponse(404, ex.getMessage(),HttpStatus.NOT_FOUND);
        return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidItemFormat.class)
    public final ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse=new ExceptionResponse(400, ex.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
}
