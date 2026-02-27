package com.github.trstndev.medimanager.model.reference;

import com.github.trstndev.medimanager.model.Physician;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "roles")
public class Role {

    // Technical primary key via UUID
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // Internal administrative code
    @Column(name = "role_code", unique = true, nullable = false, length = 10)
    private String roleCode; // e.g., "DOC", "REG"

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Column(name = "role_title", length = 20)
    private String roleTitle; // e.g., "Dr.", "Prof,"

    // One Role has Many Physicians
    @OneToMany(mappedBy = "role")
    private List<Physician> physiciansInRole;

    public Role() {}

    // GETTERS & SETTERS
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getRoleCode() { return roleCode; }
    public void setRoleCode(String roleCode) { this.roleCode = roleCode; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    public String getRoleTitle() { return roleTitle; }
    public void setRoleTitle(String roleTitle) { this.roleTitle = roleTitle; }

    public List<Physician> getPhysiciansInRole() { return physiciansInRole; }
    public void setPhysiciansInRole(List<Physician> physiciansInRole) { this.physiciansInRole = physiciansInRole; }
}
