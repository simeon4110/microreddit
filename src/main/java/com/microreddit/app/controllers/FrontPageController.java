package com.microreddit.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Basic front page render, there is nothing here right now, just a blank page.
 *
 * @author Josh Harkema
 */
@Controller
public class FrontPageController {
    @GetMapping("/")
    public String showFrontPage() {
        return "index";
    }
}
