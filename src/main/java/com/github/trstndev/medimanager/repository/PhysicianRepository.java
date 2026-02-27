package com.github.trstndev.medimanager.repository;

import com.github.trstndev.medimanager.model.Physician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicianRepository extends JpaRepository<Physician, String> {

}
