package com.example.merchmarket.domain.admin.auth.service;

import com.example.merchmarket.domain.admin.auth.dto.request.AdminSignUpRequest;
import com.example.merchmarket.domain.admin.auth.entity.Admin;
import com.example.merchmarket.domain.admin.auth.repository.AdminAuthRepository;
import com.example.merchmarket.domain.auth.dto.request.LoginRequest;
import com.example.merchmarket.domain.auth.dto.response.AuthResponse;
import com.example.merchmarket.domain.member.role.MemberRole;
import com.example.merchmarket.global.exception.ServerException;
import com.example.merchmarket.global.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.merchmarket.domain.member.role.MemberRole.ROLE_ADMIN;
import static com.example.merchmarket.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AdminAuthService {

    private final AdminAuthRepository adminAuthRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public AuthResponse adminSignUp(AdminSignUpRequest request) {
        if (adminAuthRepository.existsByEmail(request.getEmail())) {
            throw new ServerException(USER_EMAIL_DUPLICATION);
        }

        if (adminAuthRepository.existsByName(request.getName())) {
            throw new ServerException(USER_NAME_DUPLICATION);
        }

        Admin savedAdmin = Admin.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(MemberRole.of(String.valueOf(ROLE_ADMIN)))
                .build();
        adminAuthRepository.save(savedAdmin);

        String accessToken = jwtUtil.createAccessToken(
                savedAdmin.getId(),
                savedAdmin.getEmail(),
                savedAdmin.getRole()
        );
        return new AuthResponse(accessToken);
    }

    @Transactional
    public AuthResponse adminLogin(LoginRequest request) {
        Admin findAdmin = adminAuthRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new ServerException(USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), findAdmin.getPassword())) {
            throw new ServerException(INVALID_PASSWORD);
        }

        String accessToken = jwtUtil.createAccessToken(
                findAdmin.getId(),
                findAdmin.getEmail(),
                findAdmin.getRole()
        );
        return new AuthResponse(accessToken);
    }
}
