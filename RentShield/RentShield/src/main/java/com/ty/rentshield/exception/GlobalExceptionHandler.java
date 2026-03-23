package com.ty.rentshield.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ty.rentshield.response.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	/////////////////////////////////// 404 Error  /////////////////////////////////////////////////////
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleResourceNotFound(ResourceNotFoundException ex) {

        ResponseStructure<String> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
        structure.setMessage("Resource Not Found");
        structure.setData(ex.getMessage());

        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
    }

    //////////////////////////////////////////// Generic Error  /////////////////////////////////////////////////////////
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseStructure<String>> handleGenericException(Exception ex) {

        ResponseStructure<String> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        structure.setMessage("Something went wrong");
        structure.setData(ex.getMessage());

        return new ResponseEntity<>(structure, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
}
