package com.ahason.sso.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "sessions")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 512)
    private String accessToken;

    @Column(nullable = false)
    private ZonedDateTime accessTokenExpiration;

    @Column(nullable = false, length = 512)
    private String refreshToken;

    @Column(nullable = false)
    private ZonedDateTime refreshTokenExpiration;

    private ZonedDateTime createdAt = ZonedDateTime.now();
}
