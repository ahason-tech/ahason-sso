package com.ahason.sso.repository;

import com.ahason.sso.model.OTPVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OTPRepository extends JpaRepository<OTPVerification, Long> {
    Optional<OTPVerification> findTopByPhoneOrderByCreatedAtDesc(String phone);
}
