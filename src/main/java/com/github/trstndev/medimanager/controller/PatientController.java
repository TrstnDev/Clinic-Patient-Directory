package com.github.trstndev.medimanager.controller;

import com.github.trstndev.medimanager.model.Patient;
import com.github.trstndev.medimanager.repository.PatientRepository;
import com.github.trstndev.medimanager.repository.PhysicianRepository;
import com.github.trstndev.medimanager.repository.DiagnosisRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Controller
public class PatientController {

    // Dependencies are made final and use constructor injection
    private final PatientRepository patientRepository;
    private final PhysicianRepository physicianRepository;
    private final DiagnosisRepository diagnosisRepository;

    public PatientController(PatientRepository patientRepository, PhysicianRepository physicianRepository, DiagnosisRepository diagnosisRepository) {
        this.patientRepository = patientRepository;
        this.physicianRepository = physicianRepository;
        this.diagnosisRepository = diagnosisRepository;
    }


    // GET requests
    @GetMapping("/patients")
    public String listPatients(
            @RequestParam(name = "searchType", required = false) String searchType,
            @RequestParam(name = "keyword", required = false) String keyword,
            Model model) {

        List<Patient> patients;

        // Search logic
        if (keyword != null && !keyword.trim().isEmpty() && searchType != null) {
            switch (searchType) {
                case "Name":
                    patients = patientRepository.findByPatientNameContainingIgnoreCase(keyword);
                    break;
                case "Surname":
                    patients = patientRepository.findByPatientSurnameContainingIgnoreCase(keyword);
                    break;
                case "File Number":
                    patients = patientRepository.findByPatientFileNumberContainingIgnoreCase(keyword);
                    break;
                case "RSA ID":
                    Optional<Patient> exactMatch = patientRepository.findByPatientRsaId(keyword);
                    patients = exactMatch.map(List::of).orElseGet(List::of);
                    break;
                case "Diagnosis":
                    patients = patientRepository.findByPrimaryDiagnosis_DiagnosisNameContainingIgnoreCase(keyword);
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

        // Pass everything to Thymeleaf view
        model.addAttribute("patients", patients);
        model.addAttribute("newPatient", new Patient());
        model.addAttribute("physiciansList", physicianRepository.findAll());
        model.addAttribute("diagnosesList", diagnosisRepository.findAll());

        // Retain search terms in GUI
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        return "patients";
    }

    // POST requests
    @PostMapping("/patients")
    public String savePatient(@Valid @ModelAttribute("newPatient") Patient patient, BindingResult bindingResult, Model model) {

        // Check if custom validations failed
        if (bindingResult.hasErrors()) {
            // if there are errors reload the dropdowns before sending user back to the form
            model.addAttribute("patients", patientRepository.findAll());
            model.addAttribute("physiciansList", physicianRepository.findAll());
            model.addAttribute("diagnosesList", diagnosisRepository.findAll());

            // return the HTML page without redirecting so thymeleaf can display errors
            return "patients";
        }

        patientRepository.save(patient);

        // Redirect to GET route to refresh the table and clear the form
        return "redirect:/patients";
    }

    // 3. Handle the Delete Request and Validation
    @PostMapping("/patients/delete-request")
    public String requestDelete(@RequestParam("patientFileNumber") String patientFileNumber, RedirectAttributes redirectAttributes, Model model) {

        // Validation 1: Check if the format is correct (3 letters, 4 numbers)
        if (patientFileNumber == null || !patientFileNumber.matches("^[A-Z]{3}\\d{4}$")) {
            redirectAttributes.addFlashAttribute("error", "Invalid File Number format. Must be 3 uppercase letters followed by 4 numbers.");
            return "redirect:/patients";
        }

        // Validation 2: Look up patient by file number
        List<Patient> foundPatients = patientRepository.findByPatientFileNumberContainingIgnoreCase(patientFileNumber);
        if (foundPatients.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "No patient found with File Number: " + patientFileNumber);
            return "redirect:/patients";
        }

        // If it passes validation, pass exact matched entity to confirmation view
        model.addAttribute("patientToDelete", foundPatients.get(0));
        return "confirm-delete";
    }

    // 4. Execute final deletion via UUID
    @PostMapping("/patients/delete-confirm")
    public String confirmDelete(@RequestParam("id") UUID id, RedirectAttributes redirectAttributes) {
        patientRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Patient successfully deleted.");
        return "redirect:/patients";
    }
}
