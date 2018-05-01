package com.microreddit.app.controllers;

import com.microreddit.app.services.PostDetailsService;
import com.microreddit.app.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Basic front page render, there is nothing here right now, just a blank page.
 *
 * @author Josh Harkema
 */
@Controller
public class FrontPageController {
    private final PostDetailsService postDetailsService;
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public FrontPageController(PostDetailsService postDetailsService, UserDetailsServiceImpl userDetailsService) {
        this.postDetailsService = postDetailsService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/")
    public String showFrontPage(Model model) {
        model.addAttribute("posts", postDetailsService.getAllPosts());

        return "index";
    }

}
