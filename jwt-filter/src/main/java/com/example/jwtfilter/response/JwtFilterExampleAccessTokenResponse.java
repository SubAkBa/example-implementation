package com.example.jwtfilter.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtFilterExampleAccessTokenResponse {
	private String accessToken;
}
