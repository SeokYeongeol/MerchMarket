package com.example.merchmarket.domain.admin.entity;

import com.example.merchmarket.domain.member.role.MemberRole;
import com.example.merchmarket.global.entity.TimeStamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Admin extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;

    @Column(unique = true, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Builder
    public Admin(String email, String password, String name, MemberRole role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    private Admin(Long id) { this.id = id; }
    public static Admin fromAuth(Long authId) { return new Admin(authId); }
}
