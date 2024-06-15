package com.example.jwtfilter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtfilter.domain.dto.MemberDto;
import com.example.jwtfilter.response.JwtFilterExampleResponse;
import com.example.jwtfilter.service.JwtFilterExampleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/jwt-filter")
public class JwtFilterExampleController {

	private final JwtFilterExampleService jwtFilterExampleService;

	@PostMapping(value = "/signup")
	public ResponseEntity<JwtFilterExampleResponse> signup(@RequestBody MemberDto memberDto) {
		return new ResponseEntity<>(jwtFilterExampleService.signup(memberDto), HttpStatus.CREATED);
	}
}
