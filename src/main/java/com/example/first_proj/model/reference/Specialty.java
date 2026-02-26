package com.example.first_proj.model.reference;

import com.example.first_proj.model.Physician;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Specialty {
    @Id
    private String specialtyCode; // e.g., "NEUR", "ORTH SURG"
    private String specialtyTitle;

    // One Specialty has Many Physicians
    @OneToMany(mappedBy = "specialty")
    private List<Physician> physiciansInSpecialty;

    public String getSpecialtyCode() {
        return specialtyCode;
    }

    public void setSpecialtyCode(String specialtyCode) {
        this.specialtyCode = specialtyCode;
    }

    public String getSpecialtyTitle() {
        return specialtyTitle;
    }

    public void setSpecialtyTitle(String specialtyTitle) {
        this.specialtyTitle = specialtyTitle;
    }

    public List<Physician> getPhysiciansInSpecialty() {
        return physiciansInSpecialty;
    }

    public void setPhysiciansInSpecialty(List<Physician> physiciansInSpecialty) {
        this.physiciansInSpecialty = physiciansInSpecialty;
    }
}
