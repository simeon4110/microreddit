package com.microreddit.app.controllers;

import com.microreddit.app.persistence.dto.UserDto;
import com.microreddit.app.persistence.models.User;
import com.microreddit.app.services.AppUserDetailsService;
import com.microreddit.app.services.UserSecurityService;
import com.microreddit.app.services.exceptions.UsernameAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContext;

/**
 * Main view controller for routing root unauthed HTTP requests. Serves the index and registration pages.
 *
 * @author Josh Harkema
 */
@Controller
public class RegistrationController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final AppUserDetailsService userService;
    private final UserSecurityService securityService;
    private final MessageSource messages;
    private final JavaMailSender mailSender;
    private final ApplicationEventPublisher eventPublisher;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public RegistrationController(AppUserDetailsService userService, UserSecurityService securityService,
                                  @Qualifier("messageSource") MessageSource messages, JavaMailSender mailSender,
                                  ApplicationEventPublisher eventPublisher, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.securityService = securityService;
        this.messages = messages;
        this.mailSender = mailSender;
        this.eventPublisher = eventPublisher;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/user/registration")
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping("/user/registration")
    public String registerUserAccount(@RequestParam(name = "username", required = true) String username,
                                      @RequestParam(name = "email", required = true) String email,
                                      @RequestParam(name = "password", required = true) String password,
                                      @RequestParam(name = "matchingPassword", required = true) String matchingPassword) {
        UserDto accountDto = new UserDto();
        accountDto.setUsername(username);
        accountDto.setEmail(email);
        accountDto.setPassword(password);
        accountDto.setMatchingPassword(matchingPassword);
        System.out.println("Registering user account with info {}" + accountDto);
        createUserAccount(accountDto);

        return "login";
    }

    private User createUserAccount(UserDto accountDto) {
        User registered;
        try {
            registered = userService.registerNewUserAccount(accountDto);
        } catch (UsernameAlreadyExistsException e) {
            return null;
        }

        return registered;
    }

    private String getAppUrl(RequestContext request) {
        return "http://" + "127.0.0.1" + ":" + "8080" + request.getContextPath();
    }
}
