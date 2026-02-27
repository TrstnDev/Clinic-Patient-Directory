package com.github.trstndev.medimanager.model.reference;

import com.github.trstndev.medimanager.model.Physician;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "specialties")
public class Specialty {

    // Technical Primary Key via UUID
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // Clinical administrative code
    @Column(name = "specialty_code", unique = true, nullable = false, length = 15)
    private String specialtyCode; // e.g., "NEUR", "ORTH SURG"

    @Column(name = "specialty_title", nullable = false)
    private String specialtyTitle;  // e.g., "Neurology", "Paediatrics"

    // One Specialty has Many Physicians
    @OneToMany(mappedBy = "specialty")
    private List<Physician> physiciansInSpecialty;

    public Specialty() {}

    // GETTERS & SETTERS
    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }

    public String getSpecialtyCode() { return specialtyCode; }
    public void setSpecialtyCode(String specialtyCode) { this.specialtyCode = specialtyCode; }

    public String getSpecialtyTitle() { return specialtyTitle; }
    public void setSpecialtyTitle(String specialtyTitle) { this.specialtyTitle = specialtyTitle; }

    public List<Physician> getPhysiciansInSpecialty() { return physiciansInSpecialty; }
    public void setPhysiciansInSpecialty(List<Physician> physiciansInSpecialty) { this.physiciansInSpecialty = physiciansInSpecialty; }
}
