package com.example.jwtfilter.domain.dto;

public record MemberDto(
	String id,
	String password,
	String name,
	int age
) { }
