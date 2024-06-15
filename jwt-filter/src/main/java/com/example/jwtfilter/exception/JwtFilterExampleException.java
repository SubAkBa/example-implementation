package com.example.jwtfilter.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class JwtFilterExampleException extends RuntimeException {

	private final String errorMessage;
	private final HttpStatus httpStatus;

	public JwtFilterExampleException(String errorMessage, HttpStatus httpStatus) {
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}
}
