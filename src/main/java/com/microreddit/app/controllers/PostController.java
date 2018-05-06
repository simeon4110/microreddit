package com.microreddit.app.controllers;

import com.microreddit.app.persistence.dto.PostDto;
import com.microreddit.app.persistence.models.Posts.Post;
import com.microreddit.app.services.PostDetailsService;
import com.microreddit.app.services.SubDetailsService;
import com.microreddit.app.services.UserDetailsServiceImpl;
import com.microreddit.app.services.exceptions.SubDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Serves all post related pages and handles all post related GET, POST, etc requests. :TODO: create post template.
 *
 * @author Josh Harkema
 */
@Controller
public class PostController {
    private final PostDetailsService postDetailsService;
    private final UserDetailsServiceImpl userDetailsService;
    private final SubDetailsService subDetailsService;

    @Autowired
    public PostController(PostDetailsService postDetailsService, UserDetailsServiceImpl userDetailsService,
                          SubDetailsService subDetailsService) {
        this.postDetailsService = postDetailsService;
        this.userDetailsService = userDetailsService;
        this.subDetailsService = subDetailsService;
    }

    @GetMapping("/posts/create")
    public String showPostCreationForm(Model model) {
        model.addAttribute("PostDto", new PostDto());
        return "create_post";
    }

    @PostMapping("/posts/create")
    public ModelAndView createPost(@ModelAttribute("PostDto") @Valid PostDto postDto,
                                   BindingResult result, WebRequest request, Errors errors) {
        postDto.setUserID(userDetailsService.loadUserIdByUsername(request.getRemoteUser()));
        System.out.println("Adding post... " + postDto.toString());

        Post newPost = createNewPost(postDto, request.getRemoteUser());

        if (newPost == null) {
            throw new SubDoesNotExistException("Sub ID: " + postDto.getSubName() + " does not exist.");
        }

        return new ModelAndView("index", "PostDto", postDto);

    }

    @GetMapping("/posts/{id}")
    public ModelAndView viewPost(@PathVariable(value = "id") String id) {
        UUID postUUID = UUID.fromString(id);
        Post post = postDetailsService.getPost(postUUID);

        return new ModelAndView("post", "Post", post);
    }

    private Post createNewPost(PostDto postDto, String username) {
        try {
            Post newPost = postDetailsService.createNewPost(postDto.getUserID(),
                    subDetailsService.getSubByName(postDto.getSubName()).getKey().getId());
            newPost.setTitle(postDto.getTitle());
            newPost.setText(postDto.getText());
            newPost.setType(postDto.getType());
            newPost.setUsername(username);
            newPost.setSubName(postDto.getSubName());
            postDetailsService.save(newPost);
            return newPost;
        } catch (SubDoesNotExistException e) {
            return null;
        }

    }

}
