package com.ahason.sso.repository;

import com.ahason.sso.model.ClientApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientAppRepository extends JpaRepository<ClientApp, Long> {
    Optional<ClientApp> findByClientIdSecret(String clientIdSecret);
}
