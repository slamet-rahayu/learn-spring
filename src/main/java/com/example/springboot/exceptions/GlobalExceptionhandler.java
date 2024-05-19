package com.example.springboot.exceptions;

import com.example.springboot.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionhandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<ApiResponse> handleValidationException(Exception e) {
        e.printStackTrace();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Bad Request!");
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException e) {
        e.printStackTrace();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Internal Server Error!");
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({NoHandlerFoundException.class, UserNotFoundException.class})
    public ResponseEntity<ApiResponse> hanleNotFoundException(NoHandlerFoundException e) {
        e.printStackTrace();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Not Found!");
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

}
