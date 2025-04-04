package com.ahason.sso.controller;

import com.ahason.sso.model.Session;
import com.ahason.sso.model.User;
import com.ahason.sso.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userinfo")
public class UserInfoController {

    private final SessionRepository sessionRepository;

    @GetMapping
    public Map<String, Object> getUserInfo(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        String token = authHeader.replace("Bearer ", "").trim();

        Session session = sessionRepository.findByAccessToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid or expired token"));

        User user = session.getUser();

        return Map.of(
            "sub", user.getUserId(),
            "username", user.getUsername(),
            "firstName", user.getFirstName(),
            "lastName", user.getLastName()
        );
    }
}
