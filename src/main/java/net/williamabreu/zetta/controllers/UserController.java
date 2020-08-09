package net.williamabreu.zetta.controllers;

import net.williamabreu.zetta.models.User;
import net.williamabreu.zetta.repository.ProfileRepository;
import net.williamabreu.zetta.repository.RoleRepository;
import net.williamabreu.zetta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ProfileRepository profileRepository;

//    @GetMapping("role/all")
//    public ModelAndView roleList() {
//        ModelAndView modelView = new ModelAndView("role-list");
//        Iterable<Role> roleObjects = roleRepository.findAll();
//        modelView.addObject("roleObjects", roleObjects);
//        return modelView;
//    }
//
//    @GetMapping("role/{id}")
//    public ModelAndView roleEdit(@PathVariable("id") long id) {
//        Role role = roleRepository.findById(id);
//        ModelAndView modelView = new ModelAndView("role-form");
//        modelView.addObject("role", role);
//        return modelView;
//    }
//
//    @PostMapping("role/{id}")
//    public String roleUpdate(@PathVariable("id") long id, Role role) {
//        Role oldRole = roleRepository.findById(id);
//        oldRole.setName(role.getName());
//        roleRepository.save(oldRole);
//        return "redirect:/role/all";
//    }

    @GetMapping("user/new")
    public String userRegister() {
        return "user-form";
    }

    @PostMapping("user/new")
    public String userSave(@RequestParam("cpf") String cpf, @RequestParam("name") String name,
                           @RequestParam("birthdate") String birthdate, @RequestParam("sex") char sex) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        Date parsedBirthdate = null;
        try {
            parsedBirthdate = (Date) sdf.parse(birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        User user = new User(cpf, name, parsedBirthdate, sex, new Date(), null, null);
        userRepository.save(user);
//        return "redirect:/user/all";
        return "redirect:/user/new";
    }
}
