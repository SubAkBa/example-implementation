package com.example.jwtfilter.domain.id;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteFoodId implements Serializable {
	private String memberId;
	private Long foodId;
}
