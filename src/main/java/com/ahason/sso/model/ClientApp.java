package com.ahason.sso.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "clients_apps")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientAppId;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false, length = 100)
    private String clientIdSecret;

    @Column(nullable = false, length = 200)
    private String redirectUri;

    private ZonedDateTime createdAt = ZonedDateTime.now();
    private ZonedDateTime modifiedAt = ZonedDateTime.now();
}
