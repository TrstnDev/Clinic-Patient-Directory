package com.github.trstndev.medimanager.model.reference;

import jakarta.persistence.*;

@Entity
public class Diagnosis {
    @Id
    private String icd10Code; // e.g., J20.9 for Bronchitis
    private String diagnosisName;
    private String diagnosisDescription;

    public Diagnosis() {}

    // GETTERS & SETTERS
    public String getIcd10Code() { return icd10Code; }
    public void setIcd10Code(String icd10Code) { this.icd10Code = icd10Code; }
    public String getDiagnosisName() { return diagnosisName; }
    public void setDiagnosisName(String diagnosisName) { this.diagnosisName = diagnosisName; }
    public String getDiagnosisDescription() { return diagnosisDescription; }
    public void setDiagnosisDescription(String diagnosisDescription) { this.diagnosisDescription = diagnosisDescription; }
}
