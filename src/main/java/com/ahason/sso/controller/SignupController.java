package com.ahason.sso.controller;

import com.ahason.sso.model.Phone;
import com.ahason.sso.model.User;
import com.ahason.sso.model.OTPVerification;
import com.ahason.sso.repository.OTPRepository;
import com.ahason.sso.repository.PhoneRepository;
import com.ahason.sso.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignupController {

    private final OTPRepository otpRepository;
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;

    @GetMapping
    public String showSignupPage() {
        return "signup";
    }

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String mobile, Model model) {
        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);
        otpRepository.save(OTPVerification.builder()
            .phone(mobile)
            .otp(otp)
            .expiresAt(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(5))
            .build());

        // TODO: Send SMS here
        System.out.println("OTP for " + mobile + " is: " + otp);

        model.addAttribute("mobile", mobile);
        return "otp-verify";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String mobile, @RequestParam String otp, HttpSession session) {
        var record = otpRepository.findTopByPhoneOrderByCreatedAtDesc(mobile);

        if (record.isPresent() && record.get().getOtp().equals(otp)
                && record.get().getExpiresAt().isAfter(ZonedDateTime.now(ZoneOffset.UTC))) {

            // Create user
            User user = userRepository.save(User.builder()
                    .username("user_" + UUID.randomUUID().toString().substring(0, 8))
                    .createdAt(ZonedDateTime.now(ZoneOffset.UTC))
                    .modifiedAt(ZonedDateTime.now(ZoneOffset.UTC))
                    .build());

            // Save verified phone and assign as primary
            Phone phone = Phone.builder()
                    .user(user)
                    .phone(mobile)
                    .isVerified(true)
                    .build();
            phoneRepository.save(phone);

            user.setPrimaryMobileId(phone.getPhoneId());
            userRepository.save(user);

            session.setAttribute("userId", user.getUserId()); // Simulated login
            return "redirect:/dashboard";
        }

        return "redirect:/signup?error";
    }

}
