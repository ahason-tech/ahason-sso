package com.ahason.sso.repository;

import com.ahason.sso.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
    Optional<Phone> findByPhone(String phone);
}
