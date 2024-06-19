package com.example.jwtfilter.domain;

import com.example.jwtfilter.domain.id.FavoriteFoodId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteFood {
	@EmbeddedId
	private FavoriteFoodId favoriteFoodId;

	@MapsId(value = "memberId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@MapsId(value = "foodId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "food_id")
	private Food food;

	@Column(name = "favorite_order")
	private Long favoriteOrder;
}
