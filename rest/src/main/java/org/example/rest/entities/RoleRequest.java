package org.example.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "role_requests")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RoleRequest {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String roleRequestId;
    private String role;
    private String userId;
    private String roleRequestFilePath;

    @Type(type = "text")
    private String roleRequestDescription;

    public RoleRequest(String role, String userId, String roleRequestFilePath, String roleRequestDescription) {
        this.role = role;
        this.userId = userId;
        this.roleRequestFilePath = roleRequestFilePath;
        this.roleRequestDescription = roleRequestDescription;
    }
}
