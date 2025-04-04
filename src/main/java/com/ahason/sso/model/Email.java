package com.ahason.sso.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "emails")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private Boolean isVerified = false;

    private ZonedDateTime createdAt = ZonedDateTime.now();
    private ZonedDateTime modifiedAt = ZonedDateTime.now();
}
