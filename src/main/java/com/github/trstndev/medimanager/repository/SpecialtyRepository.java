package com.github.trstndev.medimanager.repository;

import com.github.trstndev.medimanager.model.reference.Specialty;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, UUID> {

    // Exact match by administrative code (e.g., "NEUR")
    Optional<Specialty> findBySpecialtyCode(String specialtyCode);

    // Search by specialty's descriptive title (e.g., "Neurology")
    List<Specialty> findBySpecialtyTitleContainingIgnoreCase(String specialtyTitle);
}
