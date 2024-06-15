package com.example.jwtfilter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jwtfilter.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
}
