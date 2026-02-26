package com.example.first_proj.controller;

import com.example.first_proj.model.Patient;
import com.example.first_proj.repository.PatientRepository;
import com.example.first_proj.repository.PhysicianRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PhysicianRepository physicianRepository;

    // 1. The GET request (loads the page)
    @GetMapping("/patients")
    public String listPatients(
            @RequestParam(name = "searchType", required = false) String searchType,
            @RequestParam(name = "keyword", required = false) String keyword,
            Model model) {

        List<Patient> patients;

        // The Search logic
        if (keyword != null && !keyword.trim().isEmpty() && searchType != null) {
            switch (searchType) {
                case "Name":
                    patients = patientRepository.findByNameContainingIgnoreCase(keyword);
                    break;
                case "ID":
                    patients = patientRepository.findByIdContainingIgnoreCase(keyword);
                    break;
                case "Diagnosis":
                    patients = patientRepository.findByDiagnosisContainingIgnoreCase(keyword);
                    break;
                case "Physician":
                    patients = patientRepository.findByTreatingPhysician_PhysicianNameContainingIgnoreCase(keyword);
                    break;
                default:
                    patients = patientRepository.findAll();
            }
        } else {
            // If no search is active, show everyone
            patients = patientRepository.findAll();
        }

        // Pass the filtered list (or all patients) to the view
        model.addAttribute("patients", patients);

        // Pass the empty objects and lists needed for the "Admit Patient" form
        model.addAttribute("newPatient", new Patient());
        model.addAttribute("physiciansList", physicianRepository.findAll());

        // Pass the search terms back to the view so the search bar doesn't clear itself after clicking search
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        return "patients";
    }

    // 2. The POST request (saves the data)
    @PostMapping("/patients")
    public String savePatient(@Valid @ModelAttribute("newPatient") Patient patient, BindingResult bindingResult, Model model) {

        // Check if custom validation failed
        if (bindingResult.hasErrors()) {
            // if there are errors reload the dropdowns before sending user back to the form
            model.addAttribute("physiciansList", physicianRepository.findAll());
            // return the HTML page without redirecting so thymeleaf can display errors
            return "patients";
        }

        patientRepository.save(patient);

        // Redirect back to the GET route to refresh the table and clear the form
        return "redirect:/patients";
    }

    // 3. Handle the Delete Request and Validation
    @PostMapping("/patients/delete-request")
    public String requestDelete(@RequestParam("patientId") String patientId, RedirectAttributes redirectAttributes, Model model) {

        // Validation 1: Check if the format is correct (3 letters, 4 numbers)
        if (patientId == null || !patientId.matches("^[A-Z]{3}\\d{4}$")) {
            redirectAttributes.addFlashAttribute("error", "Invalid ID format. Must be 3 uppercase letters followed by 4 numbers.");
            return "redirect:/patients";
        }

        // Validation 2: Check if the patient exists in the database
        Optional<Patient> patientOpt = patientRepository.findById(patientId);
        if (patientOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "No patient found with ID: " + patientId);
            return "redirect:/patients";
        }

        // If it passes validation, pass the patient to a new confirmation view
        model.addAttribute("patientToDelete", patientOpt.get());
        return "confirm-delete";
    }

    // 4. Handle the Final Deletion
    @PostMapping("/patients/delete-confirm")
    public String confirmDelete(@RequestParam("patientId") String patientId, RedirectAttributes redirectAttributes) {
        patientRepository.deleteById(patientId);
        redirectAttributes.addFlashAttribute("success", "Patient successfully deleted.");
        return "redirect:/patients";
    }
}
