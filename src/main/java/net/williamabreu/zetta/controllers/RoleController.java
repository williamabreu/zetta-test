package net.williamabreu.zetta.controllers;

import net.williamabreu.zetta.models.Role;
import net.williamabreu.zetta.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("role/all")
    public ModelAndView roleList() {
        ModelAndView modelView = new ModelAndView("role-list");
        Iterable<Role> roleObjects = roleRepository.findAll();
        modelView.addObject("roleObjects", roleObjects);
        return modelView;
    }

    @GetMapping("role/{id}")
    public ModelAndView roleEdit(@PathVariable("id") long id) {
        Role role = roleRepository.findById(id);
        ModelAndView modelView = new ModelAndView("role-form");
        modelView.addObject("role", role);
        return modelView;
    }

    @PostMapping("role/{id}")
    public String roleUpdate(@PathVariable("id") long id, Role role) {
        Role oldRole = roleRepository.findById(id);
        oldRole.setName(role.getName());
        roleRepository.save(oldRole);
        return "redirect:/role/all";
    }

    @GetMapping("role/new")
    public String roleRegister() {
        return "role-form";
    }

    @PostMapping("role/new")
    public String roleSave(Role role) {
        roleRepository.save(role);
        return "redirect:/role/all";
    }
}
