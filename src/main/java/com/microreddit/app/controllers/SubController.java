package com.microreddit.app.controllers;

import com.microreddit.app.persistence.dto.SubDto;
import com.microreddit.app.persistence.models.Sub;
import com.microreddit.app.services.SubDetailsServiceImpl;
import com.microreddit.app.services.UserDetailsServiceImp;
import com.microreddit.app.services.exceptions.SubAlreadyExistsException;
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
 * Serves sub creation page and handles creation POST request. :TODO: create a sub specific "front page" styled render.
 *
 * @author Josh Harkema
 */
@Controller
public class SubController {
    private final SubDetailsServiceImpl subDetailsService;
    private final UserDetailsServiceImp userDetailsService;

    public SubController(SubDetailsServiceImpl subDetailsService, UserDetailsServiceImp userDetailsService) {
        this.subDetailsService = subDetailsService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/subs/create")
    public String showSubCreationForm(Model model) {
        model.addAttribute("SubDto", new SubDto());
        return "create_sub";
    }

    @PostMapping("/subs/create")
    public ModelAndView createSub(@ModelAttribute("SubDto") @Valid SubDto subDto,
                                  BindingResult result, WebRequest request, Errors errors) {
        subDto.setCreatorID(userDetailsService.loadUserIdByUsername(request.getRemoteUser()));
        System.out.println("Registering... " + subDto.toString());
        Sub newSub = createNewSub(subDto);

        if (newSub == null) {
            throw new SubAlreadyExistsException(subDto.getName() + ", already exists.");
        }

        return new ModelAndView("index", "sub", subDto);
    }

    private Sub createNewSub(SubDto subDto) {
        Sub newSub;

        try {
            newSub = subDetailsService.createNewSub(subDto.getName(), subDto.getDescription(), subDto.getCreatorID());
            newSub.setTitle(subDto.getTitle());
            newSub.setSidebar(subDto.getSideBar());
            newSub.setSubmissionText(subDto.getSubmissionText());
            newSub.setLanguage(subDto.getLanguage());
            newSub.setSubType(subDto.getType());
            newSub.setSubContentOption(subDto.getContentOption());
            newSub.setCustomCSS(subDto.getCustomCSS());

            subDetailsService.save(newSub);
            return newSub;
        } catch (SubAlreadyExistsException e) {
            return null;
        }
    }

}
