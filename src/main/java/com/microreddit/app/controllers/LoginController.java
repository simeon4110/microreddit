package com.microreddit.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 * Handles login page render; all the actual work is done by spring. :TODO: get the logout and roles working.
 *
 * @author Josh Harkema
 */
@Controller
public class LoginController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/logout")
    public String showLogoutForm(WebRequest request) {
        request.setAttribute("logout", "", RequestAttributes.SCOPE_REQUEST);
        return "login";
    }
}
