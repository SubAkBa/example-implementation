package com.example.jwtfilter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jwtfilter.domain.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
