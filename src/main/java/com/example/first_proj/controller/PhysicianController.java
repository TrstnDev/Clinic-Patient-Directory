package com.example.first_proj.controller;

import com.example.first_proj.model.Physician;
import com.example.first_proj.repository.PhysicianRepository;
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

    @GetMapping
    public String listPhysicians(Model model) {
        model.addAttribute("physicians", physicianRepository.findAll());
        model.addAttribute("newPhysician", new Physician());
        return "physicians";
    }

    @PostMapping
    public String savePhysician(@ModelAttribute("newPhysician") Physician physician) {
        // Here the form passes in inputFirstName, inputSurname, and specialty
        // When save() is called, the @PrePersist method automatically fires to generate the HPCSA number and the formatted "Dr. First Last" name
        physicianRepository.save(physician);
        return "redirect:/physicians";
    }

}
