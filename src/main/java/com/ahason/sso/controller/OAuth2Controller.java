package com.ahason.sso.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OAuth2Controller {

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        return "dashboard";
    }

    @GetMapping("/oauth2/success")
    public String oauth2Success() {
        return "redirect:/dashboard";
    }
}
