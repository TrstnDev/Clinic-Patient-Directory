package com.github.trstndev.medimanager.repository;

import com.github.trstndev.medimanager.model.Physician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PhysicianRepository extends JpaRepository<Physician, UUID> {

    // 1. Exact match by Regulatory ID (HPCSA Number)
    Optional<Physician> findByHpcsaNumber(String hpcsaNumber);

    // 2. Exact match by RSA ID number
    Optional<Physician> findByPhysicianRsaId(String physicianRsaId);

    // 3. Search by Physician First Name
    List<Physician> findByPhysicianNameContainingIgnoreCase(String physicianName);

    // 4. Search by Physician Surname
    List<Physician> findByPhysicianSurnameContainingIgnoreCase(String physicianSurname);

    // 5. Search by specialty name
    List<Physician> findBySpecialty_SpecialtyNameContainingIgnoreCase(String specialtyName);
}