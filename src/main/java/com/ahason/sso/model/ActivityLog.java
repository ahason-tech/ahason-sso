package com.ahason.sso.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "activity_logs")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 50)
    private String activityType; // e.g. LOGIN, SIGNUP, LOGOUT, OTP_SENT

    @Column(nullable = false, columnDefinition = "TEXT")
    private String activityDescription;

    private ZonedDateTime activityTime = ZonedDateTime.now();
}
