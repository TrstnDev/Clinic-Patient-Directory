package com.example.first_proj.repository;

import com.example.first_proj.model.reference.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    // JpaRepository provides all methods automatically
}
