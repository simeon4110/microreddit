package com.microreddit.app.controllers;

import com.microreddit.app.services.PostDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Basic front page render, there is nothing here right now, just a blank page.
 *
 * @author Josh Harkema
 */
@Controller
@CacheConfig(cacheNames = "front-page")
public class FrontPageController {
    private final PostDetailsService postDetailsService;

    @Autowired
    public FrontPageController(PostDetailsService postDetailsService) {
        this.postDetailsService = postDetailsService;
    }

    @Cacheable("front-page")
    @GetMapping("/")
    public String showFrontPage(Model model) {
        model.addAttribute("posts", postDetailsService.getAllPosts());

        return "index";
    }

    @Cacheable("for-post")
    @GetMapping("/posts/{id}")
    public String showPost() {
        return "";
    }

}
