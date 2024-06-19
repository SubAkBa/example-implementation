package com.example.jwtfilter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwtfilter.domain.Member;
import com.example.jwtfilter.domain.dto.FavoriteFoodDto;
import com.example.jwtfilter.domain.dto.FoodDto;
import com.example.jwtfilter.domain.dto.MemberDto;
import com.example.jwtfilter.domain.dto.MemberLoginDto;
import com.example.jwtfilter.response.JwtFilterExampleAccessTokenResponse;
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

	@PostMapping(value = "/login")
	public ResponseEntity<JwtFilterExampleAccessTokenResponse> login(@RequestBody MemberLoginDto loginDto) {
		return new ResponseEntity<>(jwtFilterExampleService.login(loginDto), HttpStatus.OK);
	}

	@PostMapping(value = "/food")
	public ResponseEntity<JwtFilterExampleResponse> addFood(@AuthenticationPrincipal Member member, @RequestBody FoodDto foodDto) {
		return new ResponseEntity<>(jwtFilterExampleService.addFood(member, foodDto), HttpStatus.CREATED);
	}

	@GetMapping(value = "/food")
	public ResponseEntity<FavoriteFoodDto> getFavoriteFoodList(@AuthenticationPrincipal Member member) {
		return new ResponseEntity<>(jwtFilterExampleService.getFavoriteFoodList(member), HttpStatus.OK);
	}
}
