package com.github.trstndev.medimanager.model.reference;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "diagnoses")
public class Diagnosis {

    // Technical primary key via UUID
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // Clinical reference code
    @Column(name = "icd_10_code", unique = true, nullable = false, length = 10)
    private String icd10Code; // e.g., J20.9 for Bronchitis

    @Column(name = "diagnosis_name", nullable = false)
    private String diagnosisName;

    @Column(name = "diagnosis_description", columnDefinition = "TEXT")
    private String diagnosisDescription;

    public Diagnosis() {}

    // GETTERS & SETTERS

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getIcd10Code() { return icd10Code; }
    public void setIcd10Code(String icd10Code) { this.icd10Code = icd10Code; }

    public String getDiagnosisName() { return diagnosisName; }
    public void setDiagnosisName(String diagnosisName) { this.diagnosisName = diagnosisName; }

    public String getDiagnosisDescription() { return diagnosisDescription; }
    public void setDiagnosisDescription(String diagnosisDescription) { this.diagnosisDescription = diagnosisDescription; }
}
