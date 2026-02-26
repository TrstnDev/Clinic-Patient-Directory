package com.example.first_proj.model;

import jakarta.persistence.*;

import java.util.Locale;
import java.util.Random;

@Entity
public class Physician {

    @Id
    private String hpcsaNumber; // The Primary Key

    // These fields are used to capture the form data
    @Transient // @Transient means 'dont save this specific column to the database'"
    private String inputFirstName;

    @Transient
    private String inputSurname;

    // These fields ARE saved to the database
    private String physicianName;
    private String specialty;

    public Physician() {}

    // GETTERS
    public String getHpcsaNumber() { return hpcsaNumber; }
    public String getInputFirstName() { return inputFirstName; }
    public String getInputSurname() { return inputSurname; }
    public String getPhysicianName() { return physicianName; }
    public String getSpecialty() { return specialty; }

    // SETTERS
    public void setHpcsaNumber(String hpcsaNumber) { this.hpcsaNumber = hpcsaNumber; }
    public void setInputFirstName(String inputFirstName) { this.inputFirstName = inputFirstName; }
    public void setInputSurname(String inputSurname) { this.inputSurname = inputSurname; }
    public void setPhysicianName(String physicianName) { this.physicianName = physicianName; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    @PrePersist
    public void formatDataAndGenerateId() {
        // 1. Format the display name
        if (this.inputFirstName != null && this.inputSurname != null) {
            this.physicianName = "Dr. " + this.inputFirstName.trim() + " " + this.inputSurname.trim();
        }

        // 2. Generate the HPCSA Number (e.g., JOHSMI12345)
        if (this.hpcsaNumber == null) {
            String firstPart = String.format("%-3s", this.inputFirstName).replace(' ', 'X').substring(0, 3).toUpperCase();
            String secondPart = String.format("%-3s", this.inputSurname).replace(' ', 'X').substring(0, 3).toUpperCase();
            String randomNums = String.format("%05d", new Random().nextInt(100000));

            this.hpcsaNumber = firstPart + secondPart + randomNums;
        }
    }
}
