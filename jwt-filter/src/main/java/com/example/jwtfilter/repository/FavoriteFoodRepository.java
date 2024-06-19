package com.example.jwtfilter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.jwtfilter.domain.FavoriteFood;
import com.example.jwtfilter.domain.id.FavoriteFoodId;

public interface FavoriteFoodRepository extends JpaRepository<FavoriteFood, FavoriteFoodId> {

	@Query("SELECT ff FROM FavoriteFood ff "
		+ "JOIN Food f ON ff.favoriteFoodId.foodId = f.id "
		+ "JOIN Member m ON ff.favoriteFoodId.memberId = m.memberId "
		+ "WHERE ff.favoriteFoodId.memberId = :memberId")
	List<FavoriteFood> findFavoriteFoodsByMemberId(String memberId);
}
