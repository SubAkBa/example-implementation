package com.example.jwtfilter.provider;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.ZonedDateTime;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.jwtfilter.domain.Member;
import com.example.jwtfilter.service.MemberService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtProvider {

	private final SecretKey secretKey;
	private final Long expirationTime;
	private final MemberService memberService;

	public JwtProvider(
		@Value("${jwt.secret-key}") String secretKey,
		@Value("${jwt.expiration-time}") Long expirationTime,
		MemberService memberService
	) {
		this.secretKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());;
		this.expirationTime = expirationTime;
		this.memberService = memberService;
	}

	public String generateToken(Member member) {
		ZonedDateTime now = ZonedDateTime.now();
		ZonedDateTime expirationTime = now.plusSeconds(this.expirationTime);

		return Jwts.builder()
			.claim("id", member.getMemberId())
			.claim("password", member.getPassword())
			.issuedAt(Date.from(now.toInstant()))
			.expiration(Date.from(expirationTime.toInstant()))
			.signWith(secretKey)
			.compact();
	}

	public Authentication getAuthentication(String token) {
		Claims claims = Jwts.parser()
			.verifyWith(secretKey)
			.build()
			.parseSignedClaims(token)
			.getPayload();

		Member member = (Member)memberService.loadUserByUsername(claims.get("id", String.class));

		return new UsernamePasswordAuthenticationToken(member, token, null);
	}

	public boolean isValidToken(String token) {
		try {
			Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}
}
