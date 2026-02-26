package com.example.first_proj.model;

import java.util.Random;
import java.time.LocalDate;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Patient {

    @Id
    private String id;
    private String name;
    private String diagnosis;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate admissionDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dischargeDate;


    // Relationship: Many Patients to One Physician
    // This automatically creates a foreign key column called "treating_physician_hpcsa_number" in the Patient table
    @ManyToOne
    @JoinColumn(name = "treating_physician_hpcsa_number")
    private Physician treatingPhysician;

    //Standard empty constructor required by Spring
    public Patient() {}


    // GETTERS
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDiagnosis() { return diagnosis; }
    public LocalDate getAdmissionDate() { return admissionDate; }
    public LocalDate getDischargeDate() { return dischargeDate; }
    public Physician getTreatingPhysician() { return treatingPhysician; }


    // SETTERS
    public void setName(String name) { this.name = name; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public void setAdmissionDate(LocalDate admissionDate) { this.admissionDate = admissionDate; }
    public void setDischargeDate(LocalDate dischargeDate) { this.dischargeDate = dischargeDate; }
    public void setTreatingPhysician(Physician treatingPhysician) { this.treatingPhysician = treatingPhysician; }



    // This method runs automatically right before the object is inserted into SQL Server
    @PrePersist
    public void generateCustomId() {
        if (this.id == null) {
            // 1. Get first 3 letters. If name is shorter than 3 letters, pad it with 'X'
            String prefix = String.format("%-3s", this.name).replace(' ', 'X').substring(0, 3).toUpperCase();

            // 2. Generate 4 random digits
            int randomNum = new Random().nextInt(10000);
            String suffix = String.format("%04d", randomNum);

            // 3. Combine them
            this.id = prefix + suffix;
        }
    }
}
