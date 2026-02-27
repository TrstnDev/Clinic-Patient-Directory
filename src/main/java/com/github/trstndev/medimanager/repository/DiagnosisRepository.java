package com.github.trstndev.medimanager.repository;

import com.github.trstndev.medimanager.model.reference.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, String> {
}
