package com.TODO.TODO.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleResourceNotFound(ResourseNotFoundException ex)
    {
        HashMap<String,String> response=new HashMap<>();
        response.put("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationErrors(MethodArgumentNotValidException ex)
    {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handlingGenericException(Exception ex)
    {
        HashMap<String,String> response=new HashMap<>();
        response.put("Error"," There is something went wrong,This is a generic Exception");
        response.put("Details", ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
