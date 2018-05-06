package com.microreddit.app.controllers;

import com.microreddit.app.persistence.models.Posts.Post;
import com.microreddit.app.services.PostDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Basic front page render, there is nothing here right now, just a blank page.
 *
 * @author Josh Harkema
 */
@Controller
@CacheConfig(cacheNames = "front-page")
public class FrontPageController {
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int INITIAL_PAGE = 0;

    private final PostDetailsService postDetailsService;

    @Autowired
    public FrontPageController(PostDetailsService postDetailsService) {
        this.postDetailsService = postDetailsService;
    }

    @Cacheable("front-page")
    @GetMapping("/")
    public String showFrontPage(Model model, HttpServletRequest request) {
        int page;
        int pageSize;

        if (request.getParameter("page") == null || request.getParameter("pageSize") == null) {
            page = INITIAL_PAGE;
            pageSize = DEFAULT_PAGE_SIZE;
        } else {
            page = Integer.parseInt(request.getParameter("page"));
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        }

        List<Post> posts = postDetailsService.getPage(CassandraPageRequest.of(page, pageSize));
        model.addAttribute("posts", posts);

        return "index";
    }

}
