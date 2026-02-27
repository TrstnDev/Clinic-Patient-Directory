package com.github.trstndev.medimanager.repository;

import com.github.trstndev.medimanager.model.reference.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, String> {
}
