package com.ahason.sso.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String firstName;
    private String lastName;

    private Integer gender; // 0 = unknown, 1 = male, 2 = female

    private LocalDate dob;

    @Column(name = "primary_mobile_id")
    private Long primaryMobileId;

    @Column(name = "primary_email_id")
    private Long primaryEmailId;

    @Column(nullable = false, updatable = false)
    @Builder.Default
    private ZonedDateTime createdAt = ZonedDateTime.now(ZoneOffset.UTC);

    @Builder.Default
    private ZonedDateTime modifiedAt = ZonedDateTime.now(ZoneOffset.UTC);
}
