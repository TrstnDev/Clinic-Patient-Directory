package com.github.trstndev.medimanager.repository;

import com.github.trstndev.medimanager.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

    // 1. Search by Patient First Name
    List<Patient> findByPatientNameContainingIgnoreCase(String patientName);

    // 2. Search by Patient Surname
    List<Patient> findByPatientSurnameContainingIgnoreCase(String patientSurname);

    // 3. Search by Medical Record Number
    List<Patient> findByPatientFileNumberContainingIgnoreCase(String patientFileNumber);

    // 4. Exact match by national RSA ID number
    Optional<Patient> findByPatientRsaId(String patientRsaId);

    // 5. Search by Primary Diagnosis
    List<Patient> findByPrimaryDiagnosis_DiagnosisNameContainingIgnoreCase(String diagnosisName);

    // 6. Search by Treating Physician's Name
    List<Patient> findByTreatingPhysician_PhysicianNameContainingIgnoreCase(String physicianName);
}
