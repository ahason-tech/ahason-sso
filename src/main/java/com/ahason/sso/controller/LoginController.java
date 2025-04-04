package com.ahason.sso.controller;

import com.ahason.sso.model.OTPVerification;
import com.ahason.sso.model.User;
import com.ahason.sso.repository.OTPRepository;
import com.ahason.sso.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final UserRepository userRepository;
    private final OTPRepository otpRepository;

    @GetMapping
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/password")
    public String loginWithPassword(@RequestParam String mobile,
                                    @RequestParam String password, // Not implemented yet
                                    Model model,
                                    HttpSession session) {
        // TODO: Match phone from Phone table + verify password
        model.addAttribute("error", "Password login not yet implemented.");
        return "login";
    }

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String mobile, Model model) {
        String otp = String.valueOf((int) (Math.random() * 900000) + 100000);
        otpRepository.save(OTPVerification.builder()
            .phone(mobile)
            .otp(otp)
            .expiresAt(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(5))
            .build());

        System.out.println("OTP for login: " + otp);
        model.addAttribute("mobile", mobile);
        return "otp-verify";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String mobile,
                            @RequestParam String otp,
                            HttpSession session) {

        Optional<OTPVerification> record = otpRepository.findTopByPhoneOrderByCreatedAtDesc(mobile);

        if (record.isPresent() && record.get().getOtp().equals(otp)
                && record.get().getExpiresAt().isAfter(ZonedDateTime.now(ZoneOffset.UTC))) {
            Optional<User> userOpt = userRepository.findAll().stream()
                .filter(u -> u.getPrimaryMobileId() == null) // Simplification until Phone table is linked
                .findFirst();

            if (userOpt.isPresent()) {
                session.setAttribute("userId", userOpt.get().getUserId());
                return "redirect:/dashboard"; // or OAuth2 consent if in flow
            }
        }

        return "redirect:/login?error";
    }
}
