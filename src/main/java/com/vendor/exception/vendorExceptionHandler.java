package com.vendor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class vendorExceptionHandler {

	@ExceptionHandler(value= {vendorNotFound.class})
	public ResponseEntity<Object> handleVendorNotFoundException(vendorNotFound vendor)
	{
		exceptionpayload response=new exceptionpayload(vendor.getMessage());
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
}
