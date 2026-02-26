package com.example.first_proj.repository;

import com.example.first_proj.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

    // 1. Search by Patient Name
    List<Patient> findByNameContainingIgnoreCase(String name);

    // 2. Search by ID
    List<Patient> findByIdContainingIgnoreCase(String id);

    // 3. Search by Diagnosis
    List<Patient> findByDiagnosisContainingIgnoreCase(String diagnosis);

    // 4. Search by Treating Physician's Name
    List<Patient> findByTreatingPhysician_PhysicianNameContainingIgnoreCase(String physicianName);
}
