package com.example.merchmarket.domain.member.repository;


import com.example.merchmarket.domain.member.entity.Member;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(@Param("email") String email);

    boolean existsByName(@Param("name") String name);

    @Query("select m from Member m where m.email = :email and m.deletedAt is null")
    Optional<Member> findByEmail(@Param("email") String email);
}
