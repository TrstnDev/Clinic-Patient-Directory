package com.github.trstndev.medimanager.controller;

import com.github.trstndev.medimanager.model.Physician;
import com.github.trstndev.medimanager.repository.PhysicianRepository;
import com.github.trstndev.medimanager.repository.RoleRepository;
import com.github.trstndev.medimanager.repository.SpecialtyRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/physicians")
public class PhysicianController {

    // Final fields with constructor injections
    private final PhysicianRepository physicianRepository;
    private final RoleRepository roleRepository;
    private final SpecialtyRepository specialtyRepository;

    public PhysicianController(PhysicianRepository physicianRepository, RoleRepository roleRepository, SpecialtyRepository specialtyRepository) {
        this.physicianRepository = physicianRepository;
        this.roleRepository = roleRepository;
        this.specialtyRepository = specialtyRepository;
    }

    // GET Requests
    @GetMapping
    public String listPhysicians(
            @RequestParam(name = "searchType", required = false) String searchType,
            @RequestParam(name = "keyword", required = false) String keyword,
            Model model) {

        List<Physician> physicians;

        // Search logic
        if (keyword != null && !keyword.trim().isEmpty() && searchType != null) {
            switch (searchType) {
                case "Name":
                    physicians = physicianRepository.findByPhysicianNameContainingIgnoreCase(keyword);
                    break;
                case "Surname":
                    physicians = physicianRepository.findByPhysicianSurnameContainingIgnoreCase(keyword);
                    break;
                case "HPCSA Number":
                    Optional<Physician> matchHpcsa = physicianRepository.findByHpcsaNumber(keyword);
                    physicians = matchHpcsa.map(List::of).orElseGet(List::of);
                    break;
                case "RSA ID":
                    Optional<Physician> matchRsa = physicianRepository.findByPhysicianRsaId(keyword);
                    physicians = matchRsa.map(List::of).orElseGet(List::of);
                    break;
                case "Specialty":
                    physicians = physicianRepository.findBySpecialty_SpecialtyNameContainingIgnoreCase(keyword);
                    break;
                default:
                    physicians = physicianRepository.findAll();
            }
        } else {
            physicians = physicianRepository.findAll();
        }

        model.addAttribute("physicians", physicians);
        model.addAttribute("newPhysician", new Physician());

        // Passing reference data to populate HTML dropdowns
        model.addAttribute("rolesList", roleRepository.findAll());
        model.addAttribute("specialtiesList", specialtyRepository.findAll());

        // Retain search terms in GUI
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        return "physicians";
    }

    @PostMapping
    public String savePhysician(@Valid@ModelAttribute("newPhysician") Physician physician, BindingResult bindingResult, Model model) {

        // if validation fails reload dropdown data and return form without saving
        if (bindingResult.hasErrors()) {
            model.addAttribute("physicians", physicianRepository.findAll());
            model.addAttribute("rolesList", roleRepository.findAll());
            model.addAttribute("specialtiesList", specialtyRepository.findAll());
            return "physicians";
        }

        physicianRepository.save(physician);
        return "redirect:/physicians";
    }

}
