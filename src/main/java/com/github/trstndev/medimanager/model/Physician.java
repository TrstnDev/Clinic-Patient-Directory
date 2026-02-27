package com.github.trstndev.medimanager.model;

import com.github.trstndev.medimanager.model.reference.Role;
import com.github.trstndev.medimanager.model.reference.Specialty;
import jakarta.persistence.*;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "physicians")
public class Physician {

    // Technical Primary Key via UUID
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // Regulatory ID (HPCSA Registration number)
    @Column(name = "hpcsa_number", unique = true, updatable = false)
    private String hpcsaNumber;

    @Column(name = "physician_rsa_id", length = 13, unique = true)
    private String physicianRsaId;

    @Transient
    private String inputFirstName;

    @Transient
    private String inputSurname;

    @Column(name = "physician_name")
    private String physicianName;

    @Column(name = "physician_surname")
    private String physicianSurname;

//======================================================RELATIONSHIPS===================================================

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

//===================================================PREPERSIST LOGIC===================================================

    @PrePersist
    public void formatDataAndGenerateId() {
        // Generate complete physician name field with title
        if (this.inputFirstName != null && this.inputSurname != null) {
            String title = (this.role != null && this.role.getRoleTitle() != null) ? this.role.getRoleTitle() : "Dr.";
            this.physicianName = title + " " + this.inputFirstName.trim();
            this.physicianSurname = this.inputSurname.trim();
        }

        // Generate a mock HPCSA number (e.g., MP123456)
        if (this.hpcsaNumber == null) {
            this.hpcsaNumber = "MP" + String.format("%06d", new Random().nextInt(1000000));
        }
    }

//===================================================GETTERS & SETTERS==================================================

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getHpcsaNumber() { return hpcsaNumber; }
    public void setHpcsaNumber(String hpcsaNumber) { this.hpcsaNumber = hpcsaNumber; }

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

}
