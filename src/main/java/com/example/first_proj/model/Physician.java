package com.example.first_proj.model;

import com.example.first_proj.model.reference.Role;
import com.example.first_proj.model.reference.Specialty;
import jakarta.persistence.*;
import java.util.List;
import java.util.Random;

@Entity
public class Physician {

    @Id
    private String hpcsaId; // The custom generated PK

    @Column(length = 13, unique = true)
    private String physicianRsaId;

    @Transient
    private String inputFirstName;

    @Transient
    private String inputSurname;

    private String physicianName;
    private String physicianSurname;

    // Many Physicians can have One Role
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // Many Physicians can have One Specialty
    @ManyToOne
    @JoinColumn(name = "specialty_code")
    private Specialty specialty;

    // One Physician treats Many Patients
    @OneToMany(mappedBy = "treatingPhysician")
    private List<Patient> treatedPatients;

    public Physician() {}

    // GETTERS & SETTERS
    public String getHpcsaId() { return hpcsaId; }
    public void setHpcsaId(String hpcsaId) { this.hpcsaId = hpcsaId; }
    public String getPhysicianRsaId() { return physicianRsaId; }
    public void setPhysicianRsaId(String physicianRsaId) { this.physicianRsaId = physicianRsaId; }
    public String getInputFirstName() { return inputFirstName; }
    public void setInputFirstName(String inputFirstName) { this.inputFirstName = inputFirstName; }
    public String getInputSurname() { return inputSurname; }
    public void setInputSurname(String inputSurname) { this.inputSurname = inputSurname; }
    public String getPhysicianName() { return physicianName; }
    public void setPhysicianName(String physicianName) { this.physicianName = physicianName; }
    public String getPhysicianSurname() { return physicianSurname; }
    public void setPhysicianSurname(String physicianSurname) { this.physicianSurname = physicianSurname; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public Specialty getSpecialty() { return specialty; }
    public void setSpecialty(Specialty specialty) { this.specialty = specialty; }
    public List<Patient> getTreatedPatients() { return treatedPatients; }
    public void setTreatedPatients(List<Patient> treatedPatients) { this.treatedPatients = treatedPatients; }

    @PrePersist
    public void formatDataAndGenerateId() {
        // Generate complete physician name field with title
        if (this.inputFirstName != null && this.inputSurname != null) {
            String title = (this.role != null && this.role.getRoleTitle() != null) ? this.role.getRoleTitle() : "Dr.";
            this.physicianName = title + " " + this.inputFirstName.trim();
            this.physicianSurname = this.inputSurname.trim();
        }

        // Generate the HPCSA Number (e.g., JOHSMI12345)
        if (this.hpcsaId == null && this.inputFirstName != null && this.inputSurname != null) {
            String firstPart = String.format("%-3s", this.inputFirstName).replace(' ', 'X').substring(0, 3).toUpperCase();
            String secondPart = String.format("%-3s", this.inputSurname).replace(' ', 'X').substring(0, 3).toUpperCase();
            String randomNums = String.format("%05d", new Random().nextInt(100000));
            this.hpcsaId = firstPart + secondPart + randomNums;
        }
    }
}
