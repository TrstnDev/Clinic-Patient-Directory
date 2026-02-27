package com.github.trstndev.medimanager.model;

import com.github.trstndev.medimanager.model.reference.Diagnosis;
import com.github.trstndev.medimanager.model.validation.ValidRsaIdMatchingDob;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "patients")
@ValidRsaIdMatchingDob
public class Patient {

    // 1. Technical primary key
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // 2. Medical record number (human-readable identification, generated in @PrePersist)
    @Column(name = "patient_file_number", unique = true, updatable = false)
    private String patientFileNumber;

    @NotNull(message = "RSA ID is required")
    @Column(name = "patient_rsa_id", length = 13, unique = true, nullable = false)
    private String patientRsaId;

    private String patientTitle;
    private String patientName;
    private String patientSurname;

    @Pattern(regexp = "^(\\+?\\d{11}|0\\d{9})$", message = "Invalid phone number format")
    private String patientCell;

    @Email(message = "Invalid email format")
    private String patientEmail;

    @Column(columnDefinition = "TEXT")
    private String patientAddress;

    @NotNull(message = "Date of Birth is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotNull(message = "Admission date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate admissionDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dischargeDate;


//==================================================RELATIONSHIPS=======================================================

    // Primary Diagnosis (Many Patients can have One Primary Diagnosis)
    @ManyToOne
    @JoinColumn(name = "primary_diagnosis_icd10")
    private Diagnosis primaryDiagnosis;

    // Differential Diagnoses (Many Patients can have Many Differentials)
    @ManyToMany
    @JoinTable(
            name = "patient_differentials",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "icd10_code")
    )

    private List<Diagnosis> patientDifferentials;


    // Treating Physician (Many Patients can be treated by One Physician)
    @ManyToOne
    @JoinColumn(name = "physician_id")
    private Physician treatingPhysician;


    public Patient() {}


//=================================PREPERSIST LOGIC FOR HUMAN-READABLE FILE NUMBER======================================

    @PrePersist
    public void generateFileNumber() {
        if (this.patientFileNumber == null && this.patientName != null) {
            // 1. Get first 3 letters. If name is shorter than 3 letters, pad it with 'X'
            String prefix = String.format("%-3s", this.patientName).replace(' ', 'X').substring(0, 3).toUpperCase();
            // 2. Generate 4 random digits
            String suffix = String.format("%04d", new Random().nextInt(10000));
            // 3. Combine them
            this.patientFileNumber = prefix + suffix;
        }
    }


//=================================================GETTERS & SETTERS====================================================

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getPatientFileNumber() {
        return patientFileNumber;
    }
    public void setPatientFileNumber(String patientFileNumber) {
        this.patientFileNumber = patientFileNumber;
    }

    public String getPatientRsaId() {
        return patientRsaId;
    }
    public void setPatientRsaId(String patientRsaId) {
        this.patientRsaId = patientRsaId;
    }

    public String getPatientTitle() {
        return patientTitle;
    }
    public void setPatientTitle(String patientTitle) {
        this.patientTitle = patientTitle;
    }

    public String getPatientName() {
        return patientName;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSurname() {
        return patientSurname;
    }
    public void setPatientSurname(String patientSurname) {
        this.patientSurname = patientSurname;
    }

    public String getPatientCell() {
        return patientCell;
    }
    public void setPatientCell(String patientCell) {
        this.patientCell = patientCell;
    }

    public String getPatientEmail() {
        return patientEmail;
    }
    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientAddress() {
        return patientAddress;
    }
    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }
    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }
    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public Diagnosis getPrimaryDiagnosis() {
        return primaryDiagnosis;
    }
    public void setPrimaryDiagnosis(Diagnosis primaryDiagnosis) {
        this.primaryDiagnosis = primaryDiagnosis;
    }

    public List<Diagnosis> getPatientDifferentials() {
        return patientDifferentials;
    }
    public void setPatientDifferentials(List<Diagnosis> patientDifferentials) { this.patientDifferentials = patientDifferentials; }

    public Physician getTreatingPhysician() {
        return treatingPhysician;
    }
    public void setTreatingPhysician(Physician treatingPhysician) {
        this.treatingPhysician = treatingPhysician;
    }

}
