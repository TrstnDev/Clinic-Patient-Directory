package com.github.trstndev.medimanager.repository;

import com.github.trstndev.medimanager.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

    // 1. Search by Patient Name
    List<Patient> findByPatientNameContainingIgnoreCase(String patientName);

    // 2. Search by ID
    List<Patient> findByPatientIdContainingIgnoreCase(String patientId);

    // 3. Search by Diagnosis
    List<Patient> findByPrimaryDiagnosis_DiagnosisNameContainingIgnoreCase(String diagnosisName);

    // 4. Search by Treating Physician's Name
    List<Patient> findByTreatingPhysician_PhysicianNameContainingIgnoreCase(String physicianName);
}
