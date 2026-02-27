package com.github.trstndev.medimanager.repository;

import com.github.trstndev.medimanager.model.reference.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, UUID> {

    // 1. Exact match by the clinical code (e.g., "J20.9")
    Optional<Diagnosis> findByIcd10Code(String icd10Code);

    // 2. Search by disease name
    List<Diagnosis> findByDiagnosisNameContainingIgnoreCase(String diagnosisName);

    // 3. Search by category prefix (e.g., finding all "J20" variants)
    List<Diagnosis> findByIcd10CodeStartingWithIgnoreCase(String prefix);

}
