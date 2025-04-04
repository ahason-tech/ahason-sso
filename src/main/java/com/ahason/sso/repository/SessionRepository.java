package com.ahason.sso.repository;

import com.ahason.sso.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByAccessToken(String accessToken);
    Optional<Session> findByRefreshToken(String refreshToken);
}
