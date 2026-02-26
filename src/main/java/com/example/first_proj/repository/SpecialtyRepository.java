package com.example.first_proj.repository;

import com.example.first_proj.model.reference.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, String> {
}
