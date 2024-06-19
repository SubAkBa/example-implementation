package com.example.jwtfilter.init;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.jwtfilter.domain.FavoriteFood;
import com.example.jwtfilter.domain.Food;
import com.example.jwtfilter.domain.Member;
import com.example.jwtfilter.domain.id.FavoriteFoodId;
import com.example.jwtfilter.repository.FavoriteFoodRepository;
import com.example.jwtfilter.repository.FoodRepository;
import com.example.jwtfilter.repository.MemberRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class initData {

	private final PasswordEncoder passwordEncoder;
	private final MemberRepository memberRepository;
	private final FoodRepository foodRepository;
	private final FavoriteFoodRepository favoriteFoodRepository;

	@PostConstruct
	public void init() {
		Member member1 = Member.builder()
			.memberId("test1")
			.password(passwordEncoder.encode("test1"))
			.memberAge(10)
			.memberName("test1")
			.build();
		memberRepository.save(member1);

		Member member2 = Member.builder()
			.memberId("test2")
			.password(passwordEncoder.encode("test2"))
			.memberAge(20)
			.memberName("test2")
			.build();
		memberRepository.save(member2);

		Food food1 = Food.builder()
			.foodName("food1")
			.registerId(member2.getMemberId())
			.build();
		foodRepository.save(food1);

		Food food2 = Food.builder()
			.foodName("food2")
			.registerId(member1.getMemberId())
			.build();
		foodRepository.save(food2);

		FavoriteFood favoriteFood1 = FavoriteFood.builder()
			.favoriteFoodId(new FavoriteFoodId(member1.getMemberId(), food1.getId()))
			.member(member1)
			.food(food1)
			.favoriteOrder(1L)
			.build();
		favoriteFoodRepository.save(favoriteFood1);

		FavoriteFood favoriteFood2 = FavoriteFood.builder()
			.favoriteFoodId(new FavoriteFoodId(member2.getMemberId(), food2.getId()))
			.member(member2)
			.food(food2)
			.favoriteOrder(1L)
			.build();
		favoriteFoodRepository.save(favoriteFood2);
	}
}
