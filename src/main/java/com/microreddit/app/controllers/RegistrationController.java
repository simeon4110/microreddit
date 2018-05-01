package com.microreddit.app.controllers;

import com.microreddit.app.persistence.dto.UserDto;
import com.microreddit.app.persistence.models.User;
import com.microreddit.app.services.UserDetailsServiceImpl;
import com.microreddit.app.services.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Serves registration pages and deals with registration POST requests.
 *
 * @author Josh Harkema
 */
@Controller
public class RegistrationController {
    private final UserDetailsServiceImpl userService;

    @Autowired
    public RegistrationController(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/user/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("UserDto", new UserDto());
        return "registration";
    }

    @PostMapping("/user/registration")
    public ModelAndView registerUserAccount(@ModelAttribute("UserDto") @Valid UserDto accountDto,
                                            BindingResult result, WebRequest request, Errors errors) {

        System.out.println("Registering..." + accountDto.toString());
        User registered = createUserAccount(accountDto);

        return new ModelAndView("login", "user", accountDto);
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
}
