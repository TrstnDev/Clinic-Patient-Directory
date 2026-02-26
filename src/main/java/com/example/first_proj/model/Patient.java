package com.example.first_proj.model;

import java.util.List;
import java.util.Random;
import java.time.LocalDate;
import com.example.first_proj.model.reference.Diagnosis;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import com.example.first_proj.model.validation.ValidRsaIdMatchingDob;

@Entity
@ValidRsaIdMatchingDob
public class Patient {
    @Id
    private String patientId;

    @NotNull(message = "RSA ID is required")
    @Column(length = 13, unique = true)
    private String patientRsaId;

    private String patientTitle;
    private String patientName;
    private String patientSurname;
    private String patientCell;
    private String patientEmail;
    private String patientAddress;

    @NotNull(message = "Date of Birth is required")
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private LocalDate admissionDate;
    private LocalDate dischargeDate;

    // 1. The Primary Diagnosis (Many Patients can have One primary Diagnosis)
    @ManyToOne
    @JoinColumn(name = "primary_diagnosis_icd10")
    private Diagnosis primaryDiagnosis;

    // 2. The Differential Diagnoses (Many Patients can have Many Differentials)
    @ManyToMany
    @JoinTable(name = "patient_differentials", joinColumns = @JoinColumn(name = "patient_id"), inverseJoinColumns = @JoinColumn(name = "icd10_code"))
    private List<Diagnosis> patientDifferentials;

    // 3. The Treating Physician
    @ManyToOne
    @JoinColumn(name = "hpcsa_id")
    private Physician treatingPhysician;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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

    public void setPatientDifferentials(List<Diagnosis> patientDifferentials) {
        this.patientDifferentials = patientDifferentials;
    }

    public Physician getTreatingPhysician() {
        return treatingPhysician;
    }

    public void setTreatingPhysician(Physician treatingPhysician) {
        this.treatingPhysician = treatingPhysician;
    }

    // This method runs automatically right before the object is inserted into SQL Server
    @PrePersist
    public void generateCustomId() {
        if (this.patientId == null) {
            // 1. Get first 3 letters. If name is shorter than 3 letters, pad it with 'X'
            String prefix = String.format("%-3s", this.patientName).replace(' ', 'X').substring(0, 3).toUpperCase();

            // 2. Generate 4 random digits
            int randomNum = new Random().nextInt(10000);
            String suffix = String.format("%04d", randomNum);

            // 3. Combine them
            this.patientId = prefix + suffix;
        }
    }
}
