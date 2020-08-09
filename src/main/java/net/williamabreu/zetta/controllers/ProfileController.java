package net.williamabreu.zetta.controllers;

import net.williamabreu.zetta.models.Profile;
import net.williamabreu.zetta.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {
    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("profile/all")
    public ModelAndView profileList() {
        ModelAndView modelView = new ModelAndView("profile-list");
        Iterable<Profile> profileObjects = profileRepository.findAll();
        modelView.addObject("profileObjects", profileObjects);
        return modelView;
    }

    @GetMapping("profile/{id}")
    public ModelAndView profileEdit(@PathVariable("id") long id) {
        Profile profile = profileRepository.findById(id);
        ModelAndView modelView = new ModelAndView("profile-form");
        modelView.addObject("profile", profile);
        return modelView;
    }

    @PostMapping("profile/{id}")
    public String profileUpdate(@PathVariable("id") long id, Profile profile) {
        Profile oldProfile = profileRepository.findById(id);
        oldProfile.setName(profile.getName());
        profileRepository.save(oldProfile);
        return "redirect:/profile/all";
    }

    @GetMapping("profile/{id}/delete")
    public String profileDelete(@PathVariable("id") long id) {
        profileRepository.deleteById(id);
        return "redirect:/profile/all";
    }

    @GetMapping("profile/new")
    public String profileRegister() {
        return "profile-form";
    }

    @PostMapping("profile/new")
    public String profileSave(Profile profile) {
        profileRepository.save(profile);
        return "redirect:/profile/all";
    }
}
