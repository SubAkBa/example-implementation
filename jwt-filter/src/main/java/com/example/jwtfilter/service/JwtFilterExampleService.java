package com.example.jwtfilter.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jwtfilter.domain.Member;
import com.example.jwtfilter.domain.dto.MemberDto;
import com.example.jwtfilter.exception.JwtFilterExampleException;
import com.example.jwtfilter.repository.MemberRepository;
import com.example.jwtfilter.response.JwtFilterExampleResponse;
import com.example.jwtfilter.util.MessageUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtFilterExampleService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final MessageUtil messageUtil;

	@Transactional
	public JwtFilterExampleResponse signup(MemberDto memberDto) {
		if (memberRepository.existsById(memberDto.getId())) {
			throw new JwtFilterExampleException(messageUtil.getMessage("signup.id.duplicated"), HttpStatus.BAD_REQUEST);
		}

		Member member = Member.builder()
			.memberId(memberDto.getId())
			.password(passwordEncoder.encode(memberDto.getPassword()))
			.memberName(memberDto.getName())
			.memberAge(memberDto.getAge())
			.build();

		memberRepository.save(member);

		return JwtFilterExampleResponse.builder()
			.msg(messageUtil.getMessage("signup.success"))
			.build();
	}
}
