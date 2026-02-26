package com.example.first_proj.repository;

import com.example.first_proj.model.Physician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicianRepository extends JpaRepository<Physician, String> {

}
