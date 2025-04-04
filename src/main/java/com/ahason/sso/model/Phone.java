package com.ahason.sso.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "phones")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phoneId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column(nullable = false)
    private Boolean isVerified = false;

    private ZonedDateTime createdAt = ZonedDateTime.now();
    private ZonedDateTime modifiedAt = ZonedDateTime.now();
}
