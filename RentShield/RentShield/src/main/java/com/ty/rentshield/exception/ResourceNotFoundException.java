package com.ty.rentshield.exception;

public class ResourceNotFoundException extends RuntimeException {
//	private String message;
	private static final long serialVersionUID = 1L;
	
    public ResourceNotFoundException(String message) {
        super(message);
//        this.message = message;
    }

//    @Override
//    public String getMessage() {
//        return message;
//    }
}
