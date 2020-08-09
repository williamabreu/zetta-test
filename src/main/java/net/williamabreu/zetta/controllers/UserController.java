package net.williamabreu.zetta.controllers;

import net.williamabreu.zetta.models.Role;
import net.williamabreu.zetta.models.User;
import net.williamabreu.zetta.repository.ProfileRepository;
import net.williamabreu.zetta.repository.RoleRepository;
import net.williamabreu.zetta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("user/all")
    public ModelAndView userList() {
        ModelAndView modelView = new ModelAndView("user-list");
        Iterable<User> userObjects = userRepository.findAll();
        modelView.addObject("userObjects", userObjects);
        return modelView;
    }

    @GetMapping("user/{id}")
    public ModelAndView userEdit(@PathVariable("id") long id) {
        User user = userRepository.findById(id);
        Iterable<Role> roleObjects = roleRepository.findAll();
        ModelAndView modelView = new ModelAndView("user-form");
        modelView.addObject("user", user);
        modelView.addObject("roleObjects", roleObjects);
        return modelView;
    }

    @PostMapping("user/{id}")
    public String userUpdate(@PathVariable("id") long id, @RequestParam("role") long roleId) {
        User user = userRepository.findById(id);
        Role role = roleRepository.findById(roleId);
        user.setRole(role);
        userRepository.save(user);
        return "redirect:/user/all";
    }

    @GetMapping("user/new")
    public ModelAndView userRegister() {
        Iterable<Role> roleObjects = roleRepository.findAll();
        ModelAndView modelView = new ModelAndView("user-form");
        modelView.addObject("roleObjects", roleObjects);
        return modelView;
    }

    @PostMapping("user/new")
    public String userSave(@RequestParam("cpf") String cpf, @RequestParam("name") String name,
                           @RequestParam("birthdate") String birthdate, @RequestParam("sex") char sex,
                           @RequestParam("role") long roleId) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        Date parsedBirthdate = null;
        try {
            parsedBirthdate = (Date) sdf.parse(birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Role role = roleRepository.findById(roleId);
        User user = new User(cpf, name, parsedBirthdate, sex, new Date(), null, role);
        userRepository.save(user);
        return "redirect:/user/all";
    }
}
