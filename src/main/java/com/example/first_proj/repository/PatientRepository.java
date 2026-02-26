package com.example.first_proj.repository;

import com.example.first_proj.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

    // Search by Patient Name (ignores case)
    List<Patient> findByNameContainingIgnoreCase(String name);

    // Search by Diagnosis (ignores case)
    List<Patient> findByDiagnosisContainingIgnoreCase(String diagnosis);

    // Deep Search: Looks inside the linked Physician object to search by the Physician's name
    List<Patient> findByTreatingPhysician_PhysicianNameContainingIgnoreCase(String physicianName);
}
