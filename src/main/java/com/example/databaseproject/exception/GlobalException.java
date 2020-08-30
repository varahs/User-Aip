package com.example.databaseproject.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.databaseproject.dto.Response;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(GlobalException.class);

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error(ex.getMessage());
		Response customErrorDetails = new Response(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
		return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Response> highLevelException(UserNotFoundException ex) {
		log.error(ex.getMessage());
		Response customErrorDetails = new Response(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND.value(), null);
		return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
	}

}
