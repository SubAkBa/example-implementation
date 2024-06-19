package com.example.jwtfilter.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jwtfilter.domain.FavoriteFood;
import com.example.jwtfilter.domain.Food;
import com.example.jwtfilter.domain.Member;
import com.example.jwtfilter.domain.dto.FavoriteFoodDto;
import com.example.jwtfilter.domain.dto.FoodDto;
import com.example.jwtfilter.domain.dto.MemberDto;
import com.example.jwtfilter.domain.dto.MemberLoginDto;
import com.example.jwtfilter.exception.JwtFilterExampleException;
import com.example.jwtfilter.provider.JwtProvider;
import com.example.jwtfilter.repository.FavoriteFoodRepository;
import com.example.jwtfilter.repository.FoodRepository;
import com.example.jwtfilter.repository.MemberRepository;
import com.example.jwtfilter.response.JwtFilterExampleAccessTokenResponse;
import com.example.jwtfilter.response.JwtFilterExampleResponse;
import com.example.jwtfilter.util.MessageUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtFilterExampleService {

	private final FoodRepository foodRepository;
	private final FavoriteFoodRepository favoriteFoodRepository;
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;
	private final MessageUtil messageUtil;

	@Transactional
	public JwtFilterExampleResponse signup(MemberDto memberDto) {
		if (memberRepository.existsById(memberDto.id())) {
			throw new JwtFilterExampleException(messageUtil.getMessage("signup.id.duplicated"), HttpStatus.BAD_REQUEST);
		}

		Member member = Member.builder()
			.memberId(memberDto.id())
			.password(passwordEncoder.encode(memberDto.password()))
			.memberName(memberDto.name())
			.memberAge(memberDto.age())
			.build();

		memberRepository.save(member);

		return JwtFilterExampleResponse.builder()
			.msg(messageUtil.getMessage("signup.success"))
			.build();
	}

	@Transactional(readOnly = true)
	public JwtFilterExampleAccessTokenResponse login(MemberLoginDto loginDto) {
		Member findMember = memberRepository.findById(loginDto.id())
			.orElseThrow(() -> new JwtFilterExampleException(messageUtil.getMessage("login.id.not.exist"), HttpStatus.BAD_REQUEST));

		if (!passwordEncoder.matches(loginDto.password(), findMember.getPassword())) {
			throw new BadCredentialsException(messageUtil.getMessage("login.password.not.match"));
		}

		String token = jwtProvider.generateToken(findMember);

		return JwtFilterExampleAccessTokenResponse
			.builder()
			.accessToken(token)
			.build();
	}

	@Transactional
	public JwtFilterExampleResponse addFood(Member member, FoodDto foodDto) {
		Food food = Food.builder()
			.foodName(foodDto.foodName())
			.registerId(member.getMemberId())
			.build();

		foodRepository.save(food);

		return JwtFilterExampleResponse.builder()
			.msg(messageUtil.getMessage("add.food.success"))
			.build();
	}

	@Transactional(readOnly = true)
	public FavoriteFoodDto getFavoriteFoodList(Member member) {
		List<FavoriteFood> favoriteFoodList = favoriteFoodRepository.findFavoriteFoodsByMemberId(member.getMemberId());

		List<FoodDto> foodList = favoriteFoodList.stream()
			.map(favoriteFood -> new FoodDto(
				favoriteFood.getFood().getFoodName(),
				favoriteFood.getFavoriteOrder()
			))
			.toList();

		return new FavoriteFoodDto(member.getMemberName(), member.getMemberAge(), foodList);
	}
}
