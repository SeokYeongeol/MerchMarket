package com.example.merchmarket.domain.member.entity;

import com.example.merchmarket.domain.member.role.MemberRole;
import com.example.merchmarket.global.entity.TimeStamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true)
    private String email;
    private String password;

    @Column(length = 10, unique = true)
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    private LocalDateTime deletedAt;

    @Builder
    public Member(String email, String password, String name, MemberRole role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }
}
