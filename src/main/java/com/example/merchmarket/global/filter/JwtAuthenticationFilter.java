package com.example.merchmarket.global.filter;

import com.example.merchmarket.domain.member.role.MemberRole;
import com.example.merchmarket.global.entity.Auth;
import com.example.merchmarket.global.exception.ErrorCode;
import com.example.merchmarket.global.exception.ServerException;
import com.example.merchmarket.global.util.JwtAuthenticationToken;
import com.example.merchmarket.global.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.example.merchmarket.global.exception.ErrorCode.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException
    {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = jwtUtil.substringToken(authorizationHeader);
            try {
                Claims claims = jwtUtil.extractClaims(jwt);

                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    setAuthentication(claims);
                }
            } catch (SecurityException | MalformedJwtException e) {
                throw new ServerException(INVALID_JWT);
            } catch (ExpiredJwtException eje) {
                throw new ServerException(EXPIRED_JWT);
            } catch (UnsupportedJwtException uje) {
                throw new ServerException(UNSUPPORTED_JWT);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void setAuthentication(Claims claims) {
        Long userId = Long.valueOf(claims.getSubject());
        String email = claims.get("email", String.class);
        MemberRole role = MemberRole.of(claims.get("role", String.class));

        Auth auth = new Auth(userId, email, role);
        JwtAuthenticationToken token = new JwtAuthenticationToken(auth);
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
