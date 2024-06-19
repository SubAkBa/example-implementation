package com.example.jwtfilter.domain.dto;

import java.util.List;

public record FavoriteFoodDto(
	String memberName,
	int age,
	List<FoodDto> foodList
) { }
