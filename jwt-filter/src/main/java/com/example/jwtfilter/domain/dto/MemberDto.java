package com.example.jwtfilter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberDto {

	private String id;
	private String password;
	private String name;
	private int age;
}
