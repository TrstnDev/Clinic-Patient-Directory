package com.github.trstndev.medimanager.model.reference;

import com.github.trstndev.medimanager.model.Physician;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Role {
    @Id
    private String roleId; // e.g., "DOC", "REG"
    private String roleName;
    private String roleTitle; // e.g., "Dr.", "Prof," [cite: 6]

    // One Role has Many Physicians
    @OneToMany(mappedBy = "role")
    private List<Physician> physiciansInRole;

    public Role() {}

    // GETTERS & SETTERS
    public String getRoleId() { return roleId; }
    public void setRoleId(String roleId) { this.roleId = roleId; }
    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }
    public String getRoleTitle() { return roleTitle; }
    public void setRoleTitle(String roleTitle) { this.roleTitle = roleTitle; }
    public List<Physician> getPhysiciansInRole() { return physiciansInRole; }
    public void setPhysiciansInRole(List<Physician> physiciansInRole) { this.physiciansInRole = physiciansInRole; }
}
