package edu.carroll.dogtag.web.controller;

import edu.carroll.dogtag.jpa.model.UserProfile;
import edu.carroll.dogtag.service.UserProfileService;
import edu.carroll.dogtag.web.form.UserProfileForm;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserProfileController {
    private static final Logger log = LoggerFactory.getLogger(UserProfileController.class);
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/profilesetup")
    public String profileForm(Model model) {
        model.addAttribute("userProfileForm", new UserProfileForm());
        log.info("Successfully Mapped Register page");
        return "/profilesetup";
    }

    @PostMapping("/profilesetup")
    public String profilePost(@Valid @ModelAttribute UserProfileForm userProfileForm, BindingResult result, RedirectAttributes attr) {
        UserProfile userProfile = new UserProfile();
        userProfile.setFname(userProfileForm.getFname());
        userProfile.setLname(userProfileForm.getLname());
        userProfile.setPhone(userProfileForm.getPhone());
        userProfileService.setProfile(userProfile);
        attr.addAttribute("fname", userProfile.getFname());
        attr.addAttribute("lname", userProfile.getLname());
        log.info("Registration post was successful");
        return "redirect:/traininglog";
    }

}
