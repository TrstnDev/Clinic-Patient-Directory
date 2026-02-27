package com.github.trstndev.medimanager.repository;

import com.github.trstndev.medimanager.model.reference.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    // 1. Exact match by administrative code (e.g., "DOC")
    Optional<Role> findByRoleCode(String roleCode);

    // 2. Search by role's descriptive name (e.g., "General Practitioner")
    List<Role> findByRoleNameContainingIgnoreCase(String roleName);
}
