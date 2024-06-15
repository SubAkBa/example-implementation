package com.example.jwtfilter.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.jwtfilter.response.JwtFilterExampleResponse;

@RestControllerAdvice
public class JwtFilterExampleExceptionHandler {

	@ExceptionHandler(value = JwtFilterExampleException.class)
	public ResponseEntity<JwtFilterExampleResponse> handleException(JwtFilterExampleException exception) {
		JwtFilterExampleResponse exceptionResponse = JwtFilterExampleResponse.builder()
			.msg(exception.getErrorMessage())
			.build();

		return new ResponseEntity<>(exceptionResponse, exception.getHttpStatus());
	}
}
