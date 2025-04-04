package com.ahason.sso.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "otp_verification")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OTPVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column(nullable = false, length = 10)
    private String otp;

    @Column(nullable = false)
    private ZonedDateTime expiresAt;

    @Column(nullable = false)
    private Boolean verified = false;

    private ZonedDateTime createdAt = ZonedDateTime.now();
}
