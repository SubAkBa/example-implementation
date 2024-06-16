package com.example.jwtfilter.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.jwtfilter.repository.MemberRepository;
import com.example.jwtfilter.util.MessageUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

	private final MemberRepository memberRepository;
	private final MessageUtil messageUtil;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return memberRepository.findById(username)
			.orElseThrow(() -> new UsernameNotFoundException(messageUtil.getMessage("login.id.not.exist")));
	}
}
