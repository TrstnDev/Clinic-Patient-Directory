package com.github.trstndev.medimanager.controller;

import com.github.trstndev.medimanager.model.Physician;
import com.github.trstndev.medimanager.repository.PhysicianRepository;
import com.github.trstndev.medimanager.repository.RoleRepository;
import com.github.trstndev.medimanager.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/physicians") // This applies "/physicians" to all routes in this class
public class PhysicianController {

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @GetMapping
    public String listPhysicians(Model model) {
        model.addAttribute("physicians", physicianRepository.findAll());
        model.addAttribute("newPhysician", new Physician());

        // Passing reference data to populate HTML dropdowns
        model.addAttribute("rolesList", roleRepository.findAll());
        model.addAttribute("specialtiesList", specialtyRepository.findAll());

        return "physicians";
    }

    @PostMapping
    public String savePhysician(@ModelAttribute("newPhysician") Physician physician) {
        physicianRepository.save(physician);
        return "redirect:/physicians";
    }

}
