package com.example.first_proj.controller;

import com.example.first_proj.model.Patient;
import com.example.first_proj.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    // 1. The GET request (loads the page)
    @GetMapping("/patients")
    public String listPatients(Model model) {
        // Pass the list of all patients to display in the table
        model.addAttribute("patients", patientRepository.findAll());

        // Pass a new, empty Patient object for the form to fill out
        model.addAttribute("newPatient", new Patient());

        // Return the name of the ThymeLeaf HTML template to render
        return "patients";
    }

    // 2. The POST request (saves the data)
    @PostMapping("/patients")
    public String savePatient(@ModelAttribute("newPatient") Patient patient) {
        // Spring automatically takes the form inputs and builds the 'patient' object
        // Now, we just tell the repository to save it to the SQL Server
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
